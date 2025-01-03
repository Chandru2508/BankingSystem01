# Banking System Project

## Overview

This Banking System is a Java-based backend application that simulates the core functionalities of a banking system, such as managing accounts, processing transactions, and maintaining account balances. It uses JDBC for database interactions without relying on frameworks like Spring or Hibernate.

---

## Features

1. **Account Management**:

   - Create new accounts.
   - Retrieve account details by account ID.

2. **Transaction Management**:

   - Deposit money into an account.
   - Withdraw money from an account.
   - Transfer money between accounts.
   - View the transaction history for a specific account.

3. **Database Persistence**:

   - Account and transaction data are stored in a MySQL database.

4. **Test Coverage**:

   - Unit tests for DAO classes to validate database operations.

---

## Technologies Used

- **Programming Language**: Java
- **Database**: MySQL
- **Database Connectivity**: JDBC
- **Build Tool**: Maven
- **Testing Framework**: JUnit

---

## Project Structure

```
BankingSystem/
├── src/
│   ├── main/
│   │   ├
│   │   ├
│       │   
│   │   ├── models/
│   │   │   ├── Account.java
│   │   │   ├── Transaction.java
│   │   ├── dao/
│   │   │   ├── AccountDAO.java
│   │   │   ├── TransactionDAO.java
│   │   ├── services/
│   │   │   ├── AccountService.java
│   │   │   ├── TransactionService.java
│   │   ├── utils/
│   │   │   ├── DatabaseConnection.java
│   │   ├── App.java
│   ├── test/
    │     ├
    │     ├── bankingSystem/
    │         ├── AccountDAOTest.java
    │         ├── TransactionDAOTest.java
    │         ├── TransactionDAOTest.java
    └── resources/
        ├── application.properties
        ├── schema.sql
        ├── data.sql
```

---

## Prerequisites

1. **Java Development Kit (JDK)**: Version 8 or above.
2. **MySQL Server**: Ensure MySQL is installed and running on your machine.
3. **Maven**: For dependency management and building the project.

---

## Database Setup

1. Create a MySQL database named `Banking_System`.
   ```sql
   CREATE DATABASE Banking_System;
   ```
2. Execute the `schema.sql` file in the `resources/` folder to create the necessary tables:
   ```sql
   USE Banking_System;
   SOURCE schema.sql;
   ```
3. (Optional) Execute the `data.sql` file to populate the database with sample data:
   ```sql
   SOURCE data.sql;
   ```

---

## Configuration

Update the database connection details in `application.properties`:

```
db.url=jdbc:mysql://localhost:3306/Banking_System
db.username=root
db.password=yourpassword
```

---

## How to Run

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-repository/banking-system.git
   cd banking-system
   ```

2. **Build the Project**:

   ```bash
   mvn clean install
   ```

3. **Run the Application**:

   ```bash
   java -cp target/BankingSystem-1.0-SNAPSHOT.jar com.bankingSystem.App
   ```

---

## Usage

### **Available Commands**

When the application is running, you can perform the following operations:

1. **Create Account**:
   Enter account holder name, initial balance, and account type.
2. **View Account Details**:
   Provide an account ID to view details.
3. **Deposit Money**:
   Specify account ID and amount to deposit.
4. **Withdraw Money**:
   Specify account ID and amount to withdraw.
5. **Transfer Money**:
   Specify source account ID, destination account ID, and amount.
6. **View Transaction History**:
   Provide an account ID to view all related transactions.

---

## Testing

1. **Run Tests**:
   Execute the unit tests using Maven:
   ```bash
   mvn test
   ```
2. **Test Files**:
   - `AccountDAOTest.java`: Tests database operations for account-related functionalities.
   - `TransactionDAOTest.java`: Tests database operations for transaction-related functionalities.

---

## Key Classes and Their Responsibilities

### **Models**

- **Account.java**: Represents account details.
- **Transaction.java**: Represents transaction details.

### **DAO (Data Access Objects)**

- **AccountDAO.java**: Handles database operations for accounts.
- **TransactionDAO.java**: Handles database operations for transactions.

### **Services**

- **AccountService.java**: Implements business logic for account operations.
- **TransactionService.java**: Implements business logic for transaction operations.

### **Utilities**

- **DatabaseConnection.java**: Manages the database connection.

### **Main Class**

- **App.java**: Entry point of the application.

---

## Future Enhancements

1. Add REST APIs for external integrations.
2. Implement user authentication and authorization.
3. Add more robust error handling.
4. Migrate to frameworks like Spring Boot for scalability.

---

# Banking Application Using Swing

This application is a simple banking system implemented using Java Swing. It provides a graphical user interface (GUI) for performing basic banking operations like creating accounts, viewing accounts, depositing money, withdrawing money, viewing transactions, and an admin interface for managing accounts.

## Features

1. **Create Account**
   - Allows users to create a new bank account by entering the account type and initial balance.

2. **View Account**
   - Displays the details of a specific account by entering its account ID.

3. **Deposit Money**
   - Enables users to deposit money into an account after verifying the account ID.

4. **Withdraw Money**
   - Enables users to withdraw money from an account after verifying the account ID.

5. **View Transactions**
   - Displays all transactions for a specific account ID on a new page.

6. **Admin Interface**
   - Admin Login: Admin users can log in using a predefined username and password.
   - View All Accounts: Admin can view all user accounts with account details.
   - Delete Account: Admin can delete a user account by entering the account ID.

7. **Exit**
   - Closes the application.

## How It Works

### Main Components
- **`JFrame`**: The main application window.
- **`JPanel`**: Each section (e.g., Deposit, View Account) is displayed using a new panel.
- **Switching Panels**: The `switchPanel` method dynamically changes the visible content of the `JFrame`.

### Switching Between Panels
The `switchPanel` method replaces the current content of the frame with a new panel, ensuring a seamless transition between different parts of the application.
```java
private void switchPanel(JPanel panel) {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(panel);
    frame.revalidate();
    frame.repaint();
}
```

### Deposit/Withdraw Flow
- The account ID is verified first.
- After verification, the amount field and action buttons (Deposit/Withdraw) are enabled.

### Viewing Transactions
- Opens a new panel with a list of all transactions for the entered account ID.

## Code Structure

### Main Classes

1. **`BankingAppSwing`**
   - Contains the GUI logic for the application.
   - Handles user interactions and calls appropriate methods from the service layer.

2. **`AccountService`**
   - Manages account-related operations, such as creating and fetching account details.

3. **`TransactionService`**
   - Handles transaction-related operations, such as recording deposits and withdrawals.

### Key Methods

#### `initialize()`
Sets up the main menu with buttons for all features:
```java
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
```

#### `openDepositPanel()`
Handles the deposit process:
- Verifies the account ID.
- Enables the amount field and deposit button after successful verification.
- Calls the `TransactionService` to record the deposit.

#### `openViewTransactionsPanel()`
Displays transactions on a new page.
```java
private void openViewTransactionsPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    JTextField accountIdField = new JTextField();
    JButton viewButton = new JButton("View Transactions");

    panel.add(new JLabel("Enter Account ID:"), BorderLayout.NORTH);
    panel.add(accountIdField, BorderLayout.CENTER);
    panel.add(viewButton, BorderLayout.SOUTH);

    viewButton.addActionListener(e -> {
        long accountId = Long.parseLong(accountIdField.getText());
        try {
            var transactions = transactionService.getTransactionsByAccountId(accountId);
            JPanel transactionPanel = new JPanel(new GridLayout(transactions.size(), 1));
            transactions.forEach(t -> transactionPanel.add(new JLabel(t.toString())));

            switchPanel(transactionPanel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    switchPanel(panel);
}
```

## Setup Instructions

1. **Prerequisites**
   - Java Development Kit (JDK) installed.
   - A database connection configured for the `AccountService` and `TransactionService` classes.

2. **Run the Application**
   - Compile and run the `BankingAppSwing` class.

3. **Usage**
   - Use the buttons to navigate between features.
   - Follow the prompts in each panel for input.


## Future Enhancements
- Add validation for user inputs.
- Implement pagination for transaction views.
- Enhance the admin interface with more features.
- Add themes and styles to improve UI aesthetics.

## Dependencies
- Java Swing (built-in).
- JDBC for database connectivity.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## Author
This application was created as a learning exercise for implementing GUIs in Java. Please feel free to suggest improvements or report issues.

[Hemachandran S]



