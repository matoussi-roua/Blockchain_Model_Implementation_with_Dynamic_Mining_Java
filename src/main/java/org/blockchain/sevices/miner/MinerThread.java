package org.blockchain.sevices.miner;

import org.blockchain.models.Block;
import org.blockchain.models.BlockChain;
import org.blockchain.models.Miner;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MinerThread implements Runnable{
    private final Miner miner;
    private final Block block;
    private final BlockChain blockchain;
    private final MinerService minerService;
    private final AtomicBoolean blockMined;

    public MinerThread(Miner miner, Block block, BlockChain blockchain, MinerService minerService, AtomicBoolean blockMined) {
        this.miner = miner;
        this.block = block;
        this.blockchain = blockchain;
        this.minerService = minerService;
        this.blockMined = blockMined;
    }
    @Override
    public void run() {
        try {
            System.out.println("Miner " + miner.getMinerId() + " with hash power " + miner.getHashPower() + " started mining...");
            String result = minerService.mineBlock(miner, block, blockchain, blockMined);
            if (blockMined.get()) {
                System.out.println("Miner " + miner.getMinerId() + " mined the block! Hash: " + result);
            } else {
                System.out.println("Miner " + miner.getMinerId() + " stopped mining as the block was already mined.");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
