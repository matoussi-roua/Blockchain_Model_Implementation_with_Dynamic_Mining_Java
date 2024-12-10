package org.blockchain.sevices.miner;

import org.blockchain.models.Block;
import org.blockchain.models.BlockChain;
import org.blockchain.models.Miner;
import org.blockchain.sevices.block.BlockService;
import org.blockchain.sevices.blockchain.BlockchainService;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class MinerServiceImpl implements MinerService {
    private final BlockService blockService;
    private final BlockchainService blockchainService ;

    public MinerServiceImpl(BlockService blockService, BlockchainService blockchainService) {
        this.blockService = blockService;
        this.blockchainService = blockchainService;
    }



    /*@Override
    public String mineBlock(Miner miner, Block block, BlockChain blockchain) throws NoSuchAlgorithmException {
        String target = new String(new char[block.getDifficulty()]).replace('\0', '0');
        String calculatedHash;
        long nonceValue = 0;
        String baseHash = blockService.calculateHash(block.getVersion() + block.getPreviousHash() + block.getTxRoot());

        do {
            calculatedHash = blockService.calculateHash(baseHash + nonceValue);
            nonceValue++;
        } while (!calculatedHash.startsWith(target));

        // Set mined block details
        block.setNonce(nonceValue - 1);
        block.setHash(calculatedHash);

        // Get the previous block's hash (assumes you have a method to retrieve it)
        String previousBlockHash = blockchainService.getPreviousBlockHash(blockchain);

        // Validate and add the block to the blockchain
        if (blockchainService.validateBlock(block, calculatedHash, previousBlockHash)) {
            block.setAddedToChain(true);
            blockchainService.addBlockToBlockchain(block,blockchain);
            miner.setCryptocurrency(miner.getCryptocurrency()+1);
            return calculatedHash;
        } else {
            return "Failed to validate and add block to the blockchain.";
        }
    }*/
    public String mineBlock(Miner miner, Block block, BlockChain blockchain, AtomicBoolean blockMined) throws NoSuchAlgorithmException, InterruptedException {
        String target = new String(new char[block.getDifficulty()]).replace('\0', '0');
        String calculatedHash;
        long nonceValue = 0;
        String baseHash = blockService.calculateHash(block.getVersion() + block.getPreviousHash() + block.getTxRoot());

        // Calculate delay based on miner's hash power
        int delay = 1/miner.getHashPower(); // Higher hash power = smaller delay

        while (!blockMined.get()) { // Check if block has already been mined
            calculatedHash = blockService.calculateHash(baseHash + nonceValue);
            nonceValue++;

            if (calculatedHash.startsWith(target) && blockMined.compareAndSet(false, true)) { // If this thread mines the block
                synchronized (block) {
                    block.setNonce(nonceValue - 1);
                    block.setHash(calculatedHash);
                }
                if (blockchainService.validateBlock(block, calculatedHash, blockchainService.getPreviousBlockHash(blockchain))) {
                    synchronized (blockchain) {
                        blockchainService.addBlockToBlockchain(block, blockchain);
                    }
                    miner.setCryptocurrency(miner.getCryptocurrency() + 1);
                    return calculatedHash;
                }
            }

            // Add delay to simulate mining speed based on hash power
            Thread.sleep(delay);
        }
        return "Block already mined!";
    }
}