package org.blockchain.sevices.miner;

import org.blockchain.models.Block;
import org.blockchain.sevices.block.BlockServiceImp;

import java.security.NoSuchAlgorithmException;

public class MinerServiceImpl implements MinerService {
    private final BlockServiceImp blockService;

    public MinerServiceImpl(BlockServiceImp blockService) {
        this.blockService = blockService;
    }

    @Override
    public String mineBlock(Block block) throws NoSuchAlgorithmException {

        while(!block.getAddedToChain()) {
            String target = new String(new char[block.getDifficulty()]).replace('\0', '0');
            // Initialize variables
            String calculatedHash;
            long nonceValue = 0; // Start with nonce value of 0

            // Keep mining (incrementing nonce) until the hash matches the target
            String baseHash = blockService.calculateHash(block.getVersion() + block.getPreviousHash() + block.getTxRoot());
            do {
                calculatedHash = blockService.calculateHash(baseHash + nonceValue);
                nonceValue++;
            } while (!calculatedHash.startsWith(target));
            // Return the calculated hash once the correct one is found
            validateBlock(calculatedHash, block.getDifficulty());
            return calculatedHash;
        }
        return  null;
    }

    private void validateBlock(String calculatedHash, int difficulty) {
        if (!calculatedHash.matches("^0{" + difficulty + "}.*")) {
            throw new IllegalStateException("Block hash does not meet difficulty requirements.");
        }
    }

    // Set the target difficulty string (e.g., "0000" for difficulty of 4)
}
