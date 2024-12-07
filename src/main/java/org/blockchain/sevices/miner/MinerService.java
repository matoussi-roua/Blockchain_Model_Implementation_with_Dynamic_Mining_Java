package org.blockchain.sevices.miner;

import org.blockchain.models.Block;

import java.security.NoSuchAlgorithmException;

public interface MinerService {
    String mineBlock(Block block) throws NoSuchAlgorithmException;
}
