package org.blockchain.sevices.block;

import org.blockchain.models.Block;
import org.blockchain.models.Transaction;

import java.rmi.server.UID;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BlockService {

    Block createBlock(Long blockId, String version, int difficulty, String previousHash, List<Transaction> transactions) throws NoSuchAlgorithmException;

}
