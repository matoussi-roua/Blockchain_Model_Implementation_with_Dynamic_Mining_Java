package org.blockchain.sevices.blockchain;

import org.blockchain.models.Block;
import org.blockchain.models.BlockChain;

import java.security.NoSuchAlgorithmException;

public interface BlockchainService {
    boolean validateBlock(Block block, String calculatedHash, String previousBlockHash) throws NoSuchAlgorithmException;



    void addBlockToBlockchain(Block block, BlockChain blockChain);

    //to fix this it must take a blockchain id and return hash of the previous
    String getPreviousBlockHash(BlockChain blockchain);
}
