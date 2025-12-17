package Mini_ATM;

import java.util.ArrayList;
import java.util.Scanner;

class Bank {
    private double balance = 0;

    // Store transaction history
    private ArrayList<String> history = new ArrayList<>();

    // Authentication
    public boolean authenticate(String username, String password) {
        return username.equalsIgnoreCase("Admin") &&
                password.equals("admin123");
    }

    // Deposit money
    public void deposit(int amount) {
        balance += amount;
        history.add("Deposited: " + amount + " | Balance: " + balance);
        System.out.println(amount + " is credited to your account.");
        System.out.println("Your current balance is " + balance);
    }

    // Withdraw money
    public void withdraw(int amount) {
        if (amount > balance) {
            System.out.println("Insufficient Balance.");
        } else {
            balance -= amount;
            history.add("Withdrawn: " + amount + " | Balance: " + balance);
            System.out.println(amount + " is withdrawn from your account.");
            System.out.println("Your current balance is " + balance);
        }
    }

    // Show balance
    public void showBalance() {
        System.out.println("Current Balance in your account is " + balance);
    }

    // Show transaction history
    public void showTransactionHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions done yet.");
        } else {
            System.out.println("----- Transaction History -----");
            for (String h : history) {
                System.out.println(h);
            }
        }
    }
}

public class ATM {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        // LOGIN (MAX 3 ATTEMPTS)
        int attempts = 3;
        boolean loggedIn = false;

        while (attempts > 0) {
            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (bank.authenticate(username, password)) {
                loggedIn = true;
                System.out.println("\nLogin Successful!\n");
                break;
            } else {
                attempts--;
                System.out.println("Wrong credentials! Attempts left: " + attempts);
            }
        }

        if (!loggedIn) {
            System.out.println("Account locked. Try again later.");
            sc.close();
            return;
        }

        // MENU
        int choice;
        do {
            System.out.println("===== BANK MENU =====");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    bank.deposit(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    bank.withdraw(sc.nextInt());
                    break;

                case 3:
                    bank.showBalance();
                    break;

                case 4:
                    bank.showTransactionHistory();
                    break;

                case 5:
                    System.out.println("Thank you! Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice !!!");
            }
            System.out.println();

        } while (choice != 5);

        sc.close();
    }
}
