package org.blockchain.sevices.blockchain;

import org.blockchain.models.Block;
import org.blockchain.models.BlockChain;
import org.blockchain.sevices.block.BlockService;
import org.blockchain.sevices.block.BlockServiceImp;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

public class BlockchainServiceImpl implements BlockchainService {
    private final BlockService blockService ;
    public static final Logger log = Logger.getLogger(BlockchainServiceImpl.class.getName());


    public BlockchainServiceImpl(BlockService blockService) {
        this.blockService = blockService;
    }


    @Override
    public boolean validateBlock(Block block, String calculatedHash, String previousBlockHash) throws NoSuchAlgorithmException {
        // 1. Verify Block Structure
        log.info("Validating block " + block.getHash());
        if (block == null || block.getTransactions() == null || block.getTxRoot() == null) {
            System.err.println("Invalid block structure.");
            return false;
        }

        // 2. Verify Hash
        if (!calculatedHash.equals(block.getHash())) {
            System.err.println("Block hash does not match the calculated hash.");
            return false;
        }

        // 3. Check Proof of Work
        if (!calculatedHash.matches("^0{" + block.getDifficulty() + "}.*")) {
            System.err.println("Block hash does not meet the required difficulty.");
            return false;
        }

        // 4. Verify Transactions
       /* for (Transaction tx : block.getTransactions()) {
            if (!validateTransaction(tx)) {
                System.err.println("Invalid transaction detected.");
                return false;
            }
        }*/

        // 5. Validate Merkle Root
        String recalculatedMerkleRoot = blockService.merkelTree(block.getTransactions());
        if (!recalculatedMerkleRoot.equals(block.getTxRoot())) {
            System.err.println("Merkle root does not match the transactions.");
            return false;
        }

        // 6. Verify Previous Block Hash
        if (previousBlockHash != (block.getPreviousHash())) {
            System.err.println("Previous block hash mismatch.");
            return false;
        }

        // 7. Validate Timestamp
        if (block.getTimestamp() == null || block.getTimestamp().isAfter(LocalDateTime.now())) {
            System.err.println("Invalid timestamp.");
            return false;
        }

        return true; // All checks passed
    }

    // Helper method to validate individual transactions
   /* private boolean validateTransaction(Transaction transaction) {
        // Placeholder logic; implement your specific rules
        return transaction != null && transaction.isValid();
    }*/


    @Override
    public void addBlockToBlockchain(Block block, BlockChain blockChain) {
        log.info("Adding block " + block.getHash());
        if ((blockChain.getBlocks().isEmpty() && block.getPreviousHash() == null) || (block.getPreviousHash() != null&& block.getPreviousHash().equals(getPreviousBlockHash(blockChain)))) {

            blockChain.setBlocks(block);
        }
    }

    //to fix this it must take a blockchain id and return hash of the previous
    @Override
    public String getPreviousBlockHash(BlockChain blockchain) {
        log.info("Getting previous block hash.");
        if (blockchain.getBlocks().size() != 0) {
            return blockchain.getBlocks().get(blockchain.getBlocks().size() - 1).getHash();
        }
        log.info("genesus block!");
        return null;
    }
}
