package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import main.models.Account;
import main.services.AccountService;
import main.services.TransactionService;

public class BankingAppSwing {
    private JFrame frame;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private Container c;

    public BankingAppSwing() {
        accountService = new AccountService();
        transactionService = new TransactionService();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300, 90, 900, 600);
        frame.setResizable(true);

        c = frame.getContentPane();
        c.setLayout(null);

        JLabel title = new JLabel("Banking System");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        JButton createAccountButton = createMenuButton("Create Account", 100);
        JButton viewAccountButton = createMenuButton("View Account", 150);
        JButton depositButton = createMenuButton("Deposit Money", 200);
        JButton withdrawButton = createMenuButton("Withdraw Money", 250);
        JButton viewTransactionsButton = createMenuButton("View Transactions", 300);
        JButton adminButton = createMenuButton("Admin", 350);
        JButton exitButton = createMenuButton("Exit", 400);

        createAccountButton.addActionListener(e -> openCreateAccountPanel());
        viewAccountButton.addActionListener(e -> openViewAccountPanel());
        depositButton.addActionListener(e -> openDepositPanel());
        withdrawButton.addActionListener(e -> openWithdrawPanel());
        viewTransactionsButton.addActionListener(e -> openViewTransactionsPanel());
        adminButton.addActionListener(e -> openAdminPanel());
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private JButton createMenuButton(String text, int yPos) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setSize(300, 40);
        button.setLocation(300, yPos);
        c.add(button);
        return button;
    }

private void openCreateAccountPanel() {
    JPanel panel = createFormPanel("Create Account");

    JLabel typeLabel = createLabel("Account Type:", 100);
    String[] accountTypes = { "Savings", "Current" };
    JComboBox<String> typeComboBox = new JComboBox<>(accountTypes);
    typeComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
    typeComboBox.setSize(200, 30);
    typeComboBox.setLocation(300, 100);
    panel.add(typeLabel);
    panel.add(typeComboBox);

    JLabel balanceLabel = createLabel("Initial Balance:", 150);
    JTextField balanceField = createTextField(300, 150);
    panel.add(balanceLabel);
    panel.add(balanceField);

    JButton createButton = createActionButton("Create", 200);
    JButton backButton = createActionButton("Back to Main Menu", 250);

    createButton.addActionListener(e -> {
        String type = (String) typeComboBox.getSelectedItem();
        double balance;
        try {
            balance = Double.parseDouble(balanceField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number for the initial balance.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Account account = accountService.createAccount(type, balance);
            JOptionPane.showMessageDialog(frame, "Account created successfully: " + account);
            initialize();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    backButton.addActionListener(e -> initialize());

    panel.add(createButton);
    panel.add(backButton);

    switchPanel(panel);
}


    private void openViewAccountPanel() {
        JPanel panel = createFormPanel("View Account");

        JLabel idLabel = createLabel("Enter Account ID:", 100);
        JTextField idField = createTextField(300, 100);
        panel.add(idLabel);
        panel.add(idField);

        JButton viewButton = createActionButton("View", 150);
        JButton backButton = createActionButton("Back to Main Menu", 200);

        viewButton.addActionListener(e -> {
            long accountId = Long.parseLong(idField.getText());
            try {
                Account account = accountService.getAccountDetails(accountId);
                
                if(account == null) {JOptionPane.showMessageDialog(frame, "Account Not Found! ");}
                
                else {JOptionPane.showMessageDialog(frame, "Account Details: " + account);}
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> initialize());

        panel.add(viewButton);
        panel.add(backButton);

        switchPanel(panel);
    }

private void openDepositPanel() {
    JPanel panel = createFormPanel("Deposit Money");

    JLabel idLabel = createLabel("Enter Account ID:", 100);
    JTextField idField = createTextField(300, 100);
    panel.add(idLabel);
    panel.add(idField);

    JButton verifyButton = createActionButton("Verify Account", 150);
    JButton backButton = createActionButton("Back to Main Menu", 200);

    verifyButton.addActionListener((ActionEvent e) -> {
        long accountId = Long.parseLong(idField.getText());
        try {
            Account account = accountService.getAccountDetails(accountId);
            
            if(account == null){
                JOptionPane.showMessageDialog(frame, "Account not Found.");}
            
            else{
                JOptionPane.showMessageDialog(frame, "Account verified.");
                JPanel depositPanel = createFormPanel("Deposit Money - Account Verified");

                JLabel amountLabel = createLabel("Enter Amount:", 100);
                JTextField amountField = createTextField(300, 100);
                depositPanel.add(amountLabel);
                depositPanel.add(amountField);

                JButton depositButton = createActionButton("Deposit", 150);
                JButton backToMainButton = createActionButton("Back to Main Menu", 200);

                depositButton.addActionListener(ev -> {
                    double amount = Double.parseDouble(amountField.getText());
                    try {
                        transactionService.recordTransaction(accountId, amount, "Credit");
                        JOptionPane.showMessageDialog(frame, "Deposit successful!");
                        initialize();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
                });

            backToMainButton.addActionListener(ev -> initialize());

            depositPanel.add(depositButton);
            depositPanel.add(backToMainButton);

            switchPanel(depositPanel);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: Account not found! " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    });

    backButton.addActionListener(e -> initialize());

    panel.add(verifyButton);
    panel.add(backButton);

    switchPanel(panel);
}

private void openWithdrawPanel() {
    JPanel panel = createFormPanel("Withdraw Money");

    JLabel idLabel = createLabel("Enter Account ID:", 100);
    JTextField idField = createTextField(300, 100);
    panel.add(idLabel);
    panel.add(idField);

    JButton verifyButton = createActionButton("Verify Account", 150);
    JButton backButton = createActionButton("Back to Main Menu", 200);

    verifyButton.addActionListener(e -> {
        long accountId = Long.parseLong(idField.getText());
        try {
            Account account = accountService.getAccountDetails(accountId);
            if(account == null){
                JOptionPane.showMessageDialog(frame, "Account not Found.");}
            
            else{
                JOptionPane.showMessageDialog(frame, "Account verified! ");

                JPanel withdrawPanel = createFormPanel("Withdraw Money - Account Verified");

                JLabel amountLabel = createLabel("Enter Amount:", 100);
                JTextField amountField = createTextField(300, 100);
                withdrawPanel.add(amountLabel);
                withdrawPanel.add(amountField);

                JButton withdrawButton = createActionButton("Withdraw", 150);
                JButton backToMainButton = createActionButton("Back to Main Menu", 200);

                withdrawButton.addActionListener((ActionEvent ev) -> {
                    double amount = Double.parseDouble(amountField.getText());
                    try {
                        transactionService.recordTransaction(accountId, amount, "Debit");
                        JOptionPane.showMessageDialog(frame, "Withdrawal successful!");
                        initialize();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                backToMainButton.addActionListener(ev -> initialize());

                withdrawPanel.add(withdrawButton);
                withdrawPanel.add(backToMainButton);

                switchPanel(withdrawPanel);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: Account not found! " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    backButton.addActionListener(e -> initialize());

    panel.add(verifyButton);
    panel.add(backButton);

    switchPanel(panel);
}


private void openViewTransactionsPanel() {
    JPanel panel = createFormPanel("View Transactions");

    JLabel idLabel = createLabel("Enter Account ID:", 100);
    JTextField idField = createTextField(300, 100);
    panel.add(idLabel);
    panel.add(idField);

    JButton viewButton = createActionButton("View", 150);
    JButton backButton = createActionButton("Back to Main Menu", 200);

    viewButton.addActionListener(e -> {
        long accountId = Long.parseLong(idField.getText());
        try {
            var transactions = transactionService.getTransactionsByAccountId(accountId);
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No transactions found!");
            } else {
                String[] columnNames = { "Transaction ID", "Account ID", "Amount", "Type", "Date" };
                Object[][] data = transactions.stream()
                    .map(t -> new Object[] { t.getTransactionId(), t.getAccountId(), t.getAmount(), t.getType(), t.getDate() })
                    .toArray(Object[][]::new);

                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(50, 250, 800, 300);

                panel.add(scrollPane);
                panel.revalidate();
                panel.repaint();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    backButton.addActionListener(e -> initialize());

    panel.add(viewButton);
    panel.add(backButton);

    switchPanel(panel);
}


    private void openAdminPanel() {
        JPanel panel = createFormPanel("Admin Panel");

        JLabel userLabel = createLabel("Username:", 100);
        JTextField userField = createTextField(300, 100);
        panel.add(userLabel);
        panel.add(userField);

        JLabel passLabel = createLabel("Password:", 150);
        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 15));
        passField.setSize(200, 30);
        passField.setLocation(300, 150);
        panel.add(passLabel);
        panel.add(passField);

        JButton loginButton = createActionButton("Login", 200);
        JButton backButton = createActionButton("Back to Main Menu", 250);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (username.equals("admin") && password.equals("admin123")) {
                openAdminOptionsPanel();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!");
            }
        });

        backButton.addActionListener(e -> initialize());

        panel.add(loginButton);
        panel.add(backButton);

        switchPanel(panel);
    }

    private void openAdminOptionsPanel() {
    JPanel panel = createFormPanel("Admin Options");

    JButton viewAccountsButton = createActionButton("View All Accounts", 100);
    JButton deleteAccountButton = createActionButton("Delete Account", 150);
    JButton backButton = createActionButton("Back to Main Menu", 200);

    viewAccountsButton.addActionListener(e -> {
        try {
            var accounts = accountService.getAllAccounts();
            if (accounts.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No accounts found!");
            } else {
                String[] columnNames = { "Account ID", "Type", "Balance" };
                Object[][] data = accounts.stream()
                    .map(a -> new Object[] { a.getAccountId(), a.getAccountType(), a.getBalance() })
                    .toArray(Object[][]::new);

                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(50, 250, 800, 300);

                panel.add(scrollPane);
                panel.revalidate();
                panel.repaint();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    deleteAccountButton.addActionListener(e -> {
        String accountId = JOptionPane.showInputDialog(frame, "Enter Account ID to delete:");
        try {
            accountService.deleteAccountByAccountId(Long.parseLong(accountId));
            JOptionPane.showMessageDialog(frame, "Account deleted successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    backButton.addActionListener(e -> initialize());

    panel.add(viewAccountsButton);
    panel.add(deleteAccountButton);
    panel.add(backButton);

    switchPanel(panel);
}


    private JPanel createFormPanel(String title) {
        JPanel panel = new JPanel(null);
        panel.setSize(900, 600);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        titleLabel.setSize(400, 30);
        titleLabel.setLocation(100, 30);
        panel.add(titleLabel);

        return panel;
    }

    private JLabel createLabel(String text, int yPos) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setSize(200, 30);
        label.setLocation(100, yPos);
        return label;
    }

    private JTextField createTextField(int xPos, int yPos) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setSize(200, 30);
        textField.setLocation(xPos, yPos);
        return textField;
    }

    private JButton createActionButton(String text, int yPos) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setSize(200, 30);
        button.setLocation(300, yPos);
        return button;
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
