package org.blockchain;

import org.blockchain.models.Block;
import org.blockchain.models.Miner;
import org.blockchain.models.Transaction;
import org.blockchain.sevices.block.BlockService;
import org.blockchain.sevices.block.BlockServiceImp;

import java.security.NoSuchAlgorithmException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        List<Transaction> transactions = List.of(new Transaction(0L,"fromAddress", "toAddress", "100"));
        Miner miner1 = new Miner(0L, 5);
        Miner miner2 = new Miner(1L, 7);
        // Create the BlockchainService
        BlockService blockService = new BlockServiceImp();

        // Create and mine the block
        Block block = blockService.createBlock(0L,"1.0", 4, "previousBlockHash", transactions);

        // Output the mined block details
        System.out.println("Block Version: " + block.getVersion());
        System.out.println("Block Hash: " + block.getHash());
        System.out.println("Block Nonce: " + block.getNonce());
        System.out.println("Block Timestamp: " + block.getTimestamp());
        System.out.println("Merkle Root: " + block.getTxRoot());
    }
}