/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author admin
 */
import java.sql.SQLException;
import main.models.Account;
import main.services.AccountService;
import main.services.TransactionService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        Scanner scanner = new Scanner(System.in);
        

        while (true) {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.println("7. Admin");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter account type: ");
                        String type = scanner.next();
                        System.out.print("Enter initial balance: ");
                        double balance = scanner.nextDouble();
                        Account account = accountService.createAccount(type, balance);
                        System.out.println("Account created successfully: " + account);
                    }
                    case 2 -> {
                        System.out.print("Enter account ID: ");
                        long accountId = scanner.nextLong();
                        Account account = accountService.getAccountDetails(accountId);
                        System.out.println("Account Details: " + account);
                    }
                    case 3 -> {
                        System.out.print("Enter account ID: ");
                        long accountId = scanner.nextLong();
                        System.out.print("Enter deposit amount: ");
                        double amount = scanner.nextDouble();
                        transactionService.recordTransaction(accountId, amount, "Credit");
                        System.out.println("Deposit successful");
                    }
                    case 4 -> {
                        System.out.print("Enter account ID: ");
                        long accountId = scanner.nextLong();
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();
                        transactionService.recordTransaction(accountId, amount, "Debit");
                        System.out.println("Withdrawal successful");
                    }
                    case 5 -> {
                        System.out.print("Enter account ID: ");
                        long accountId = scanner.nextLong();
                        var transactions = transactionService.getTransactionsByAccountId(accountId);
                        transactions.forEach(System.out::println);
                    }
                    case 6 -> {
                        System.out.println("Exiting... Goodbye!");
                        scanner.close();
                        return;
                    }
                    case 7 -> {
                        System.out.println("User Name: ");
                        String userName = scanner.next();
                        if(userName.equals("Chandru2728")) {
                            System.out.println("Pasword: ");
                            String passKey = scanner.next();
                            if(passKey.equals("Chandru2728@2024")) {
                                System.out.println("Enter the Option. \n 1.Delete");
                                int option = scanner.nextInt();
                                if(option == 1){
                                    System.out.println("Enter the Account ID");
                                    long id = scanner.nextLong();
                                    accountService.deleteAccountByAccountId(id);
                                }
                                
                                else {System.out.println("Invalid Option");}
                            }
                            else {System.out.println("Invalid Password");}
                        }
                        else {System.out.println("Invalid User Name");}
                        
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

