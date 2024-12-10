package org.blockchain.sevices.miner;

import org.blockchain.models.Block;
import org.blockchain.models.BlockChain;
import org.blockchain.models.Miner;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

public interface MinerService {



    //String mineBlock(Miner miner, Block block, BlockChain blockchain) throws NoSuchAlgorithmException;

    String mineBlock(Miner miner, Block block, BlockChain blockchain, AtomicBoolean blockMined) throws NoSuchAlgorithmException, InterruptedException;
}
