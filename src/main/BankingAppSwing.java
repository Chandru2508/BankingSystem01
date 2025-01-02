package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import main.models.Account;
import main.services.AccountService;
import main.services.TransactionService;

public class BankingAppSwing {
    private JFrame frame;
    private AccountService accountService;
    private TransactionService transactionService;

    public BankingAppSwing() {
        accountService = new AccountService();
        transactionService = new TransactionService();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel mainPanel = new JPanel(new GridLayout(7, 1));

        JButton createAccountButton = new JButton("Create Account");
        JButton viewAccountButton = new JButton("View Account");
        JButton depositButton = new JButton("Deposit Money");
        JButton withdrawButton = new JButton("Withdraw Money");
        JButton viewTransactionsButton = new JButton("View Transactions");
        JButton adminButton = new JButton("Admin");
        JButton exitButton = new JButton("Exit");

        createAccountButton.addActionListener(e -> openCreateAccountPanel());
        viewAccountButton.addActionListener(e -> openViewAccountPanel());
        depositButton.addActionListener(e -> openDepositPanel());
        withdrawButton.addActionListener(e -> openWithdrawPanel());
        viewTransactionsButton.addActionListener(e -> openViewTransactionsPanel());
        adminButton.addActionListener(e -> openAdminPanel());
        exitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(createAccountButton);
        mainPanel.add(viewAccountButton);
        mainPanel.add(depositButton);
        mainPanel.add(withdrawButton);
        mainPanel.add(viewTransactionsButton);
        mainPanel.add(adminButton);
        mainPanel.add(exitButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void openCreateAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField typeField = new JTextField();
        JTextField balanceField = new JTextField();
        JButton createButton = new JButton("Create");

        panel.add(new JLabel("Account Type:"));
        panel.add(typeField);
        panel.add(new JLabel("Initial Balance:"));
        panel.add(balanceField);
        panel.add(new JLabel());
        panel.add(createButton);

        createButton.addActionListener(e -> {
            String type = typeField.getText();
            double balance = Double.parseDouble(balanceField.getText());
            try {
                Account account = accountService.createAccount(type, balance);
                JOptionPane.showMessageDialog(frame, "Account created successfully: " + account);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchPanel(panel);
    }

    private void openViewAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JTextField accountIdField = new JTextField();
        JButton viewButton = new JButton("View");

        panel.add(new JLabel("Enter Account ID:"));
        panel.add(accountIdField);
        panel.add(viewButton);

        viewButton.addActionListener(e -> {
            long accountId = Long.parseLong(accountIdField.getText());
            try {
                Account account = accountService.getAccountDetails(accountId);
                JOptionPane.showMessageDialog(frame, "Account Details: " + account);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchPanel(panel);
    }
private void openDepositPanel() {
    JPanel panel = new JPanel(new GridLayout(4, 2));
    JTextField accountIdField = new JTextField();
    JTextField amountField = new JTextField();
    amountField.setEnabled(false);
    JButton verifyButton = new JButton("Verify Account");
    JButton depositButton = new JButton("Deposit");
    depositButton.setEnabled(false);

    panel.add(new JLabel("Account ID:"));
    panel.add(accountIdField);
    panel.add(verifyButton);
    panel.add(new JLabel());
    panel.add(new JLabel("Amount:"));
    panel.add(amountField);
    panel.add(depositButton);

    verifyButton.addActionListener(e -> {
        long accountId = Long.parseLong(accountIdField.getText());
        try {
            Account account = accountService.getAccountDetails(accountId);
            if (account != null) {
                JOptionPane.showMessageDialog(frame, "Account verified: " + account);
                amountField.setEnabled(true);
                depositButton.setEnabled(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    depositButton.addActionListener(e -> {
        long accountId = Long.parseLong(accountIdField.getText());
        double amount = Double.parseDouble(amountField.getText());
        try {
            transactionService.recordTransaction(accountId, amount, "Credit");
            JOptionPane.showMessageDialog(frame, "Deposit successful");
            initialize(); // Return to the main menu
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    switchPanel(panel);
}

private void openWithdrawPanel() {
    JPanel panel = new JPanel(new GridLayout(4, 2));
    JTextField accountIdField = new JTextField();
    JTextField amountField = new JTextField();
    amountField.setEnabled(false);
    JButton verifyButton = new JButton("Verify Account");
    JButton withdrawButton = new JButton("Withdraw");
    withdrawButton.setEnabled(false);

    panel.add(new JLabel("Account ID:"));
    panel.add(accountIdField);
    panel.add(verifyButton);
    panel.add(new JLabel());
    panel.add(new JLabel("Amount:"));
    panel.add(amountField);
    panel.add(withdrawButton);

    verifyButton.addActionListener(e -> {
        long accountId = Long.parseLong(accountIdField.getText());
        try {
            Account account = accountService.getAccountDetails(accountId);
            if (account != null) {
                JOptionPane.showMessageDialog(frame, "Account verified: " + account);
                amountField.setEnabled(true);
                withdrawButton.setEnabled(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    withdrawButton.addActionListener(e -> {
        long accountId = Long.parseLong(accountIdField.getText());
        double amount = Double.parseDouble(amountField.getText());
        try {
            transactionService.recordTransaction(accountId, amount, "Debit");
            JOptionPane.showMessageDialog(frame, "Withdrawal successful");
            initialize(); // Return to the main menu
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    switchPanel(panel);
}


   private void openViewTransactionsPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    JPanel inputPanel = new JPanel(new GridLayout(2, 1));
    JTextField accountIdField = new JTextField();
    JButton viewButton = new JButton("View Transactions");

    inputPanel.add(new JLabel("Enter Account ID:"));
    inputPanel.add(accountIdField);

    panel.add(inputPanel, BorderLayout.NORTH);
    panel.add(viewButton, BorderLayout.SOUTH);

    viewButton.addActionListener(e -> {
        long accountId = Long.parseLong(accountIdField.getText());
        try {
            var transactions = transactionService.getTransactionsByAccountId(accountId);
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No transactions found for this account.");
            } else {
                // Create a new panel to display the transactions
                JPanel transactionsPanel = new JPanel(new BorderLayout());
                String[] columnNames = {"Transaction ID", "Account ID", "Amount", "Type", "Date"};
                Object[][] data = new Object[transactions.size()][5];
                for (int i = 0; i < transactions.size(); i++) {
                    var transaction = transactions.get(i);
                    data[i][0] = transaction.getTransactionId();
                    data[i][1] = transaction.getAccountId();
                    data[i][2] = transaction.getAmount();
                    data[i][3] = transaction.getType();
                    data[i][4] = transaction.getDate();
                }

                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);

                JButton backButton = new JButton("Back to Main Menu");
                backButton.addActionListener(event -> initialize());

                transactionsPanel.add(new JLabel("Transaction Details", SwingConstants.CENTER), BorderLayout.NORTH);
                transactionsPanel.add(scrollPane, BorderLayout.CENTER);
                transactionsPanel.add(backButton, BorderLayout.SOUTH);

                switchPanel(transactionsPanel);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    switchPanel(panel);
}


    private void openAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("Chandru2728") && password.equals("Chandru2728@2024")) {
                openAdminOptionsPanel();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchPanel(panel);
    }

    private void openAdminOptionsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JButton deleteAccountButton = new JButton("Delete Account");

        deleteAccountButton.addActionListener(e -> {
            String accountIdStr = JOptionPane.showInputDialog(frame, "Enter Account ID to delete:");
            long accountId = Long.parseLong(accountIdStr);
            try {
                accountService.deleteAccountByAccountId(accountId);
                JOptionPane.showMessageDialog(frame, "Account deleted successfully");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(deleteAccountButton);

        switchPanel(panel);
    }

    private void switchPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankingAppSwing::new);
    }
}
