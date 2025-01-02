package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        // Create main frame
        frame = new JFrame("Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1));

        JButton createAccountBtn = new JButton("Create Account");
        JButton viewAccountBtn = new JButton("View Account");
        JButton depositBtn = new JButton("Deposit Money");
        JButton withdrawBtn = new JButton("Withdraw Money");
        JButton viewTransactionsBtn = new JButton("View Transactions");
        JButton adminBtn = new JButton("Admin");
        JButton exitBtn = new JButton("Exit");

        createAccountBtn.addActionListener(e -> openCreateAccountPanel());
        viewAccountBtn.addActionListener(e -> openViewAccountPanel());
        depositBtn.addActionListener(e -> openDepositPanel());
        withdrawBtn.addActionListener(e -> openWithdrawPanel());
        viewTransactionsBtn.addActionListener(e -> openViewTransactionsPanel());
        adminBtn.addActionListener(e -> openAdminPanel());
        exitBtn.addActionListener(e -> System.exit(0));

        mainPanel.add(createAccountBtn);
        mainPanel.add(viewAccountBtn);
        mainPanel.add(depositBtn);
        mainPanel.add(withdrawBtn);
        mainPanel.add(viewTransactionsBtn);
        mainPanel.add(adminBtn);
        mainPanel.add(exitBtn);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private void openCreateAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField typeField = new JTextField();
        JTextField balanceField = new JTextField();
        JButton createBtn = new JButton("Create");

        panel.add(new JLabel("Account Type:"));
        panel.add(typeField);
        panel.add(new JLabel("Initial Balance:"));
        panel.add(balanceField);
        panel.add(new JLabel());
        panel.add(createBtn);

        createBtn.addActionListener(e -> {
            String type = typeField.getText();
            double balance = Double.parseDouble(balanceField.getText());
            try {
                Account account = accountService.createAccount(type, balance);
                JOptionPane.showMessageDialog(frame, "Account created successfully: " + account);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchPanel(panel);
    }

    private void openViewAccountPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextField accountIdField = new JTextField();
        JButton viewBtn = new JButton("View");

        panel.add(new JLabel("Enter Account ID:"), BorderLayout.NORTH);
        panel.add(accountIdField, BorderLayout.CENTER);
        panel.add(viewBtn, BorderLayout.SOUTH);

        viewBtn.addActionListener(e -> {
            long accountId = Long.parseLong(accountIdField.getText());
            try {
                Account account = accountService.getAccountDetails(accountId);
                JOptionPane.showMessageDialog(frame, "Account Details: " + account);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchPanel(panel);
    }

    private void openDepositPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField accountIdField = new JTextField();
        JTextField amountField = new JTextField();
        JButton depositBtn = new JButton("Deposit");

        panel.add(new JLabel("Account ID:"));
        panel.add(accountIdField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel());
        panel.add(depositBtn);

        depositBtn.addActionListener(e -> {
            long accountId = Long.parseLong(accountIdField.getText());
            double amount = Double.parseDouble(amountField.getText());
            try {
                transactionService.recordTransaction(accountId, amount, "Credit");
                JOptionPane.showMessageDialog(frame, "Deposit successful");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchPanel(panel);
    }

    private void openWithdrawPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField accountIdField = new JTextField();
        JTextField amountField = new JTextField();
        JButton withdrawBtn = new JButton("Withdraw");

        panel.add(new JLabel("Account ID:"));
        panel.add(accountIdField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel());
        panel.add(withdrawBtn);

        withdrawBtn.addActionListener(e -> {
            long accountId = Long.parseLong(accountIdField.getText());
            double amount = Double.parseDouble(amountField.getText());
            try {
                transactionService.recordTransaction(accountId, amount, "Debit");
                JOptionPane.showMessageDialog(frame, "Withdrawal successful");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchPanel(panel);
    }

    private void openViewTransactionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextField accountIdField = new JTextField();
        JButton viewBtn = new JButton("View Transactions");

        panel.add(new JLabel("Enter Account ID:"), BorderLayout.NORTH);
        panel.add(accountIdField, BorderLayout.CENTER);
        panel.add(viewBtn, BorderLayout.SOUTH);

        viewBtn.addActionListener(e -> {
            long accountId = Long.parseLong(accountIdField.getText());
            try {
                var transactions = transactionService.getTransactionsByAccountId(accountId);
                JOptionPane.showMessageDialog(frame, transactions.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchPanel(panel);
    }

    private void openAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginBtn);

        loginBtn.addActionListener(e -> {
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
        JButton deleteAccountBtn = new JButton("Delete Account");

        deleteAccountBtn.addActionListener(e -> {
            String accountIdStr = JOptionPane.showInputDialog(frame, "Enter Account ID to delete:");
            long accountId = Long.parseLong(accountIdStr);
            try {
                accountService.deleteAccountByAccountId(accountId);
                JOptionPane.showMessageDialog(frame, "Account deleted successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(deleteAccountBtn);

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
