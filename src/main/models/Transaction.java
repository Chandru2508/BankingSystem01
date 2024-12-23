/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.models;

/**
 *
 * @author admin
 */
import java.time.LocalDateTime;

public class Transaction {
    private long transactionId;
    private double amount;
    private String type;
    private LocalDateTime date;
    private long accountId;

    public Transaction(long transactionId, double amount, String type, LocalDateTime date, long accountId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.accountId = accountId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "[Transaction ID: " + transactionId + "\tAmount: " + amount + "\tType: " + type + "\tDate: " + date + "]";
    }
    
}