package org.blockchain.models;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlockChain {
    private Long blockchainID;
    private List<Block> blocks;

    public BlockChain(Long blockchainID) {
        this.blockchainID = blockchainID;
        this.blocks = new ArrayList<>();
    }

    public Long getBlockchainID() {
        return blockchainID;
    }

    public void setBlockchainID(Long blockchainID) {
        this.blockchainID = blockchainID;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Block blockToAdd) {
        if (blocks.isEmpty()){
            blocks = new ArrayList<>();

        }
        blocks.add(blockToAdd);
    }
}
