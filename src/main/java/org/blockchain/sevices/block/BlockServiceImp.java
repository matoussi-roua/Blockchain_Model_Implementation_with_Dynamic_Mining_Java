package org.blockchain.sevices.block;

import org.blockchain.models.Block;
import org.blockchain.models.Transaction;

import java.nio.charset.StandardCharsets;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BlockServiceImp implements BlockService {




    //en hexadecimale
    public String calculateHash(String dataToHash) {
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

    public String merkelTree(List<Transaction> transactions) throws NoSuchAlgorithmException {
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
        // Create the block with initial parameters (without hash and nonce)
        Block block = new Block(blockId, version, difficulty, previousHash, transactions);
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