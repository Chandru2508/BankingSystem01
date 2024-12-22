/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.services;

/**
 *
 * @author admin
 */
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import main.models.Account;
import main.models.Transaction;
import main.dao.TransactionDAO;

public class TransactionService {
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final AccountService accountService = new AccountService();

    public void recordTransaction(long accountId, double amount, String type) throws SQLException {

        Account account = accountService.getAccountDetails(accountId);

        if(account == null) 
        throw new SQLException("Account not Found");

        else if(type.equalsIgnoreCase("Debit") && account.getBalance() < amount) 
        {throw new IllegalArgumentException("Insufficient Funds");}

        double newBalance = "Credit".equalsIgnoreCase(type) 
                ? account.getBalance() + amount
                : account.getBalance() - amount;

        accountService.updateAccountBalance(accountId, newBalance);

        Transaction transaction = new Transaction(0, amount, type, LocalDateTime.now(), accountId);
        
        transactionDAO.createTransaction(transaction);
    }

    public List<Transaction> getTransactionsByAccountId(long accountId) throws SQLException {
        return transactionDAO.getTransactionsByAccountId(accountId);
    }
}
