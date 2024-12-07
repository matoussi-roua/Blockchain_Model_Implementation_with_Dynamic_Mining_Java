package org.blockchain.models;

public class Transaction {
    private Long transactionId;
    private String sourceName;
    private String destinationName;
    private String amount;
    public Transaction(Long transactionId, String sourceName, String destinationName, String amount) {
        this.transactionId = transactionId;
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", sourceName='" + sourceName + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
