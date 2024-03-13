import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private Map<String, Integer> accounts;
    private String currentAccount;

    public ATM() {
        this.accounts = new HashMap<>();
        this.accounts.put("123456", 1000); // Example account with initial balance of 1000
        this.currentAccount = "";
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter account number (or type 'exit' to quit):");
            String accountNumber = scanner.nextLine();
            if (accountNumber.equals("exit")) {
                break;
            }
            if (isValidAccount(accountNumber)) {
                this.currentAccount = accountNumber;
                showMainMenu();
            } else {
                System.out.println("Invalid account number. Please try again.");
            }
        }
        System.out.println("Exiting ATM. Thank you for using our services.");
        scanner.close();
    }

    private boolean isValidAccount(String accountNumber) {
        return this.accounts.containsKey(accountNumber);
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    this.currentAccount = "";
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void checkBalance() {
        int balance = this.accounts.get(this.currentAccount);
        System.out.println("Your balance is: " + balance);
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount to deposit:");
        int amount = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        int currentBalance = this.accounts.get(this.currentAccount);
        this.accounts.put(this.currentAccount, currentBalance + amount);
        System.out.println("Deposit successful. Your new balance is: " + this.accounts.get(this.currentAccount));
    }

    private void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount to withdraw:");
        int amount = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        int currentBalance = this.accounts.get(this.currentAccount);
        if (amount <= currentBalance) {
            this.accounts.put(this.currentAccount, currentBalance - amount);
            System.out.println("Withdrawal successful. Your new balance is: " + this.accounts.get(this.currentAccount));
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
