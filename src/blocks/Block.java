package blocks;

import java.util.ArrayList;
import java.util.Date;
import transactions.TransactionContainer;

public class Block {
    
    private ArrayList<TransactionContainer> transactions;
    private int numberOfTransactions;
    private Date timestamp;


    public Block(ArrayList<TransactionContainer> transactions, int numberOfTransactions) {
        this.transactions = transactions;
        this.numberOfTransactions = numberOfTransactions;
        this.timestamp = new Date();
    }

    public ArrayList<TransactionContainer> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(ArrayList<TransactionContainer> transactions) {
        this.transactions = transactions;
    }

    public int getNumberOfTransactions() {
        return this.numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
