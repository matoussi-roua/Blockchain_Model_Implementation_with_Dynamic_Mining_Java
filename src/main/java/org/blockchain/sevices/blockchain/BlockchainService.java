package org.blockchain.sevices.blockchain;

import org.blockchain.models.Block;

public interface BlockchainService {
    void createBlockchain();
    void addBlockToBlockchain(Block block);
}
