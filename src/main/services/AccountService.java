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
import java.util.List;
import main.dao.AccountDAO;
import main.dao.TransactionDAO;
import main.models.Account;

public class AccountService {
    public AccountDAO accountDAO = new AccountDAO();
    public TransactionDAO transactionDAO = new TransactionDAO();
    
    public Account createAccount(String accountType, double initialBalance) throws SQLException {
        Account account = new Account(0, accountType, initialBalance);
        return accountDAO.createAccount(account);
        
    }
    
    public void deleteAccountByAccountId(long accountId) throws SQLException {
        
       Account account = getAccountDetails(accountId);

       if(account == null) 
       throw new SQLException("Account not Found");
       
        
       else {
           transactionDAO.deleteAccountTransactionByAccountId(accountId);
           accountDAO.deleteAccountByAccountId(accountId);
       }
        
    }

    public Account getAccountDetails(long accountId) throws SQLException {
        return accountDAO.getAccountById(accountId);
    }

    public List<Account> getAllAccounts() throws SQLException {
        return accountDAO.getAllAccounts();
    }

    public void updateAccountBalance(long accountId, double balance) throws SQLException {
        boolean updated = accountDAO.updateAccountBalance(accountId, balance);

        if(!updated) throw new SQLException("Failed to Update Account Balance.");
    }
}
