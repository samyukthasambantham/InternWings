import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal canceled.");
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int option, double amount) {
        switch (option) {
            case 1:
                if (bankAccount.withdraw(amount)) {
                    System.out.println("Withdrawal successful. Remaining balance: $" + bankAccount.getBalance());
                }
                break;
            case 2:
                bankAccount.deposit(amount);
                System.out.println("Deposit successful. Updated balance: $" + bankAccount.getBalance());
                break;
            case 3:
                System.out.println("Current balance: $" + bankAccount.getBalance());
                break;
            case 4:
                System.out.println("Exiting. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize a bank account with an initial balance
        BankAccount userAccount = new BankAccount(1000.0);

        // Create an ATM object connected to the user's bank account
        ATM atm = new ATM(userAccount);

        while (true) {
            // Display ATM options
            atm.displayOptions();

            // Get user input for option
            System.out.print("Choose an option (1-4): ");
            int option = scanner.nextInt();

            // Exit if the user chooses option 4
            if (option == 4) {
                break;
            }

            // Perform the selected transaction
            if (option == 3) {
                atm.performTransaction(option, 0); // No amount needed for checking balance
            } else {
                // Get user input for amount
                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();

                // Validate user input
                if (amount < 0) {
                    System.out.println("Invalid amount. Please enter a non-negative value.");
                    continue;
                }

                // Perform the selected transaction
                atm.performTransaction(option, amount);
            }
        }

        scanner.close();
    }
}
