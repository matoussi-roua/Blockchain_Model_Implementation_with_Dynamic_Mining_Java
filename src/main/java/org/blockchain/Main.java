package org.blockchain;

import org.blockchain.models.Block;
import org.blockchain.models.BlockChain;
import org.blockchain.models.Miner;
import org.blockchain.models.Transaction;
import org.blockchain.sevices.block.BlockService;
import org.blockchain.sevices.block.BlockServiceImp;
import org.blockchain.sevices.blockchain.BlockchainService;
import org.blockchain.sevices.blockchain.BlockchainServiceImpl;
import org.blockchain.sevices.miner.MinerService;
import org.blockchain.sevices.miner.MinerServiceImpl;
import org.blockchain.sevices.miner.MinerThread;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        BlockChain blockChain = new BlockChain(0L);

        List<Transaction> transactions = List.of(new Transaction(0L,"fromAddress", "toAddress", "100"));

        // Create the BlockchainService
        BlockService blockService = new BlockServiceImp();
        BlockchainService blockchainService = new BlockchainServiceImpl(blockService);
        MinerService minerService = new MinerServiceImpl(blockService , blockchainService);


        // Create and mine the block
        Block block = blockService.createBlock(0L,"1.0", 4, null, transactions);


        // Output the mined block details
        System.out.println("Block Version: " + block.getVersion());
        System.out.println("Block Hash: " + block.getHash());
        System.out.println("Block Nonce: " + block.getNonce());
        System.out.println("Block Timestamp: " + block.getTimestamp());
        System.out.println("Merkle Root: " + block.getTxRoot());
        AtomicBoolean blockMined = new AtomicBoolean(false);

        Miner miner1 = new Miner(1L, 5);
        Miner miner2 = new Miner(2L, 7);
        Miner miner3 = new Miner(3L, 6);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new MinerThread(miner1, block, blockChain, minerService, blockMined));
        executor.execute(new MinerThread(miner2, block, blockChain, minerService, blockMined));
        executor.execute(new MinerThread(miner3, block, blockChain, minerService, blockMined));

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all miners to finish
        }

        System.out.println("Final Block Hash: " + block.getHash());
        System.out.println("Block Nonce: " + block.getNonce());
        System.out.println("Miner 1 Cryptocurrency: " + miner1.getCryptocurrency());
        System.out.println("Miner 2 Cryptocurrency: " + miner2.getCryptocurrency());
        System.out.println("Miner 3 Cryptocurrency: " + miner3.getCryptocurrency());
    }
}