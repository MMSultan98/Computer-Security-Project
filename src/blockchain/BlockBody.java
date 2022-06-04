package blockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import transactions.Transaction;

public class BlockBody implements Serializable {
    
    private int blockID;
    private ArrayList<Transaction> transactions;
    private Date timestamp;


    public BlockBody(int blockID, ArrayList<Transaction> transactions, Date timestamp) {
        this.blockID = blockID;
        this.transactions = transactions;
        this.timestamp = new Date();
    }
    

    public int getBlockID() {
        return this.blockID;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

}
