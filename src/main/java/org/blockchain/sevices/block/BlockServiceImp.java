package org.blockchain.sevices.block;

import org.blockchain.models.Block;
import org.blockchain.models.Transaction;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockServiceImp implements BlockService {
    public static final Logger log = Logger.getLogger(BlockServiceImp.class.getName());

    @Override
    public String calculateHash(String dataToHash) {
        log.log(Level.INFO, "Calculating hash...");
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(
                    dataToHash.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashbytes);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("algorithme not found");
        }
    }

    private static String bytesToHex(byte[] hash) {

        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public String merkelTree(List<Transaction> transactions) throws NoSuchAlgorithmException {
        log.log(Level.INFO, "merkelTree calculation started");
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException("Transaction list cannot be empty.");
        }

        List<String> hashes = transactions.stream()
                .map(Transaction::toString)
                .map(this::calculateHash)
                .toList();

        while (hashes.size() > 1) {
            List<String> newLevel = new ArrayList<>();
            for (int i = 0; i < hashes.size(); i += 2) {
                String hash1 = hashes.get(i);
                String hash2 = (i + 1 < hashes.size()) ? hashes.get(i + 1) : hashes.get(i);
                newLevel.add(calculateHash(hash1 + hash2));
            }
            hashes = newLevel;
        }
        return hashes.get(0);
    }

    @Override
    public Block createBlock(Long blockId, String version, int difficulty, String previousHash, List<Transaction> transactions) throws NoSuchAlgorithmException {
        log.log(Level.INFO, "Creating block started : ");
        // Create the block with initial parameters (without hash and nonce)
        Block block = new Block(blockId, version, difficulty, previousHash, transactions);

        block.setTimestamp(LocalDateTime.now());
        block.setTxRoot(merkelTree(transactions));
        block.setHash(calculateHash(block.toString()));

        // Perform mining to find the correct hash for the block
       // String minedHash = mineBlock(block);

        // Set the mined hash to the block
        //block.setHash(minedHash);

        // Return the fully created block
        return block;
    }

}