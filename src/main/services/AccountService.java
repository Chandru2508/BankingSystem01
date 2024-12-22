/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.services;

/**
 *
 * @author admin
 */
import main.dao.AccountDAO;
import java.sql.SQLException;
import main.models.Account;

public class AccountService {
    public AccountDAO accountDAO = new AccountDAO();

    public Account createAccount(String accountType, double initialBalance) throws SQLException {
        Account account = new Account(0, accountType, initialBalance);
        return accountDAO.createAccount(account);
    }

    public Account getAccountDetails(long accountId) throws SQLException {
        return accountDAO.getAccountById(accountId);
    }

    public void updateAccountBalance(long accountId, double balance) throws SQLException {
        boolean updated = accountDAO.updateAccountBalance(accountId, balance);

        if(!updated) throw new SQLException("Failed to Update Account Balance.");
    }
}
