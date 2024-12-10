package org.blockchain.models;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Block {
    private Long blockId;
    private String version;
    private Boolean isAddedToChain;
    private String hash;
    private String previousHash;
    private long nonce;
    private LocalDateTime timestamp;
    private int difficulty;
    private List<Transaction> transactions;
    private String txRoot;

    public Block(Long blockId, String version, int difficulty, String previousHash, List<Transaction> transactions) throws NoSuchAlgorithmException {
        this.blockId = blockId;
        this.version = version;
        this.isAddedToChain = false;
        this.previousHash = previousHash;
        this.difficulty = difficulty;
        this.nonce = 0;
        this.transactions = transactions;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Boolean getAddedToChain() {
        return isAddedToChain;
    }

    public void setAddedToChain(Boolean addedToChain) {
        isAddedToChain = addedToChain;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getTxRoot() {
        return txRoot;
    }

    public void setTxRoot(String txRoot) {
        this.txRoot = txRoot;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return timestamp == block.timestamp && Objects.equals(version, block.version) && Objects.equals(hash, block.hash) && Objects.equals(previousHash, block.previousHash) && Objects.equals(nonce, block.nonce) && Objects.equals(transactions, block.transactions) && Objects.equals(txRoot, block.txRoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, hash, previousHash, nonce, timestamp, transactions, txRoot);
    }


}
