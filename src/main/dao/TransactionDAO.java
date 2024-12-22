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
import java.util.ArrayList;
import java.util.List;
import main.models.Transaction;
import main.utils.DatabaseConnection;


public class TransactionDAO {
    public Transaction createTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transactions (amount, type, date, account_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, transaction.getAmount());
            stmt.setString(2, transaction.getType());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.getDate()));
            stmt.setLong(4, transaction.getAccountId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                transaction.setAccountId(rs.getLong(1));
            }
        }
        return transaction;
    }

    public List<Transaction> getTransactionsByAccountId(long accountId) throws SQLException {
        
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accountId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(
                    rs.getLong("transaction_id"),
                    rs.getDouble("amount"),
                    rs.getString("type"),
                    rs.getTimestamp("date").toLocalDateTime(),
                    rs.getLong("account_id")
                ));
            }
        }
        return transactions;
    }
}
