package org.blockchain.models;


public class Miner {
    private Long minerId;
    private int hashPower;
    private int cryptocurrency;

    public Miner(Long minerId, int hashPower) {
        this.minerId = minerId;
        this.hashPower = hashPower;
        this.cryptocurrency = 0;
    }

    public Long getMinerId() {
        return minerId;
    }

    public void setMinerId(Long minerId) {
        this.minerId = minerId;
    }

    public int getHashPower() {
        return hashPower;
    }

    public void setHashPower(int hashPower) {
        this.hashPower = hashPower;
    }
    public int getCryptocurrency() {
        return cryptocurrency;
    }
    public void setCryptocurrency(int cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }
}
