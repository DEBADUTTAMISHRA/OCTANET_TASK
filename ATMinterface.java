import java.util.*;

class BankTransaction {
    private String transactionType;
    private double amount;
    private double balance;

    public BankTransaction(String transactionType, double amount, double balance) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;
    private List<BankTransaction> transactionHistory;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<BankTransaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposit", amount);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Funds");
            return false;
        } else {
            balance -= amount;
            addTransaction("Withdraw", amount);
            return true;
        }
    }

    public void transfer(double amount) {
        if (balance >= amount) {
            balance -= amount;
            addTransaction("Transfer", amount);
            System.out.println("Balance transferred");
        } else {
            System.out.println("Insufficient funds");
        }
    }

    private void addTransaction(String transactionType, double amount) {
        transactionHistory.add(new BankTransaction(transactionType, amount, balance));
    }
}

class ATM {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        BankAccount userAccount = new BankAccount("1234567890", 1000.0);

        System.out.println("Welcome To The ATM");
        System.out.println("1.Transaction History");
        System.out.println("2.Withdraw");
        System.out.println("3.Deposit");
        System.out.println("4.Transfer");
        System.out.println("5.Quit");

        while (true) {
            System.out.println("Enter Your Choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                     List<BankTransaction> history = userAccount.getTransactionHistory();
                    System.out.println("Transaction History:");
                    for (BankTransaction transaction : history) {
                        System.out.println(
                            "Type: " + transaction.getTransactionType() +
                            ", Amount: $" + transaction.getAmount()
                        );
                    }
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw:$");
                    double withdrawAmount = sc.nextDouble();
                    if (userAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Your new balance is:$" + userAccount.getBalance());
                    }
                    break;
                case 3:
                    System.out.println("Enter Amount To Deposit:$");
                    double depositAmount = sc.nextDouble();
                    userAccount.deposit(depositAmount);
                    System.out.println("Deposit Successful. Your new balance is:$" + userAccount.getBalance());
                    break;
                case 4:
                    System.out.println("Enter transfer amount:$");
                    double transferAmount = sc.nextDouble();
                    userAccount.transfer(transferAmount);
                    System.out.println("Your current balance:$" + userAccount.getBalance());
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Have a nice day!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
 }
}
}