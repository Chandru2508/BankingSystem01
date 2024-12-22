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

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## Author

[Hemachandran S]



