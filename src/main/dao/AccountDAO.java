/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao;

/**
 *
 * @author admin
 */
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import main.models.Account;
import main.utils.DatabaseConnection;

public class AccountDAO {
    public Account createAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (account_type, balance) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, account.getAccountType());
            stmt.setDouble(2, account.getBalance());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                account.setAccountId(rs.getLong(1));
            }
        }
        return account;
    }
    
    public void deleteAccountByAccountId(long accountId) throws SQLException {
        String sql = "delete from accounts where account_id  = ?";
        
        try(Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, accountId);
            stmt.execute();
        }
        System.out.println("Account Deleted Successfuly...");
    }

    public Account getAccountById(long accountId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accountId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                    rs.getLong("account_id"),
                    rs.getString("account_type"),
                    rs.getDouble("balance")
                );
            }
        }
        return null;
    }

    public List<Account> getAllAccounts() throws SQLException {
    String sql = "SELECT * FROM accounts";
    List<Account> accounts = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Account account = new Account(
                rs.getLong("account_id"),
                rs.getString("account_type"),
                rs.getDouble("balance")
            );
            accounts.add(account);
        }
    }
    return accounts;
}


    public boolean updateAccountBalance(long accountId, double balance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, balance);
            stmt.setLong(2, accountId);

            return stmt.executeUpdate() > 0;
        }
    }
}
