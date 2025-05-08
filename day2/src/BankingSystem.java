import java.util.*;

public class BankingSystem {
    private Map<String, Double> accounts = new HashMap<>();
    private Map<String, Double> sortedAccounts = new TreeMap<>();
    private Queue<String> withdrawalQueue = new LinkedList<>();

    public void createAccount(String accountNumber, double initialBalance) {
        accounts.put(accountNumber, initialBalance);
        sortedAccounts.put(accountNumber, initialBalance);
    }

    public void requestWithdrawal(String accountNumber, double amount) {
        withdrawalQueue.add(accountNumber + ":" + amount);
    }

    public void processWithdrawals() {
        while (!withdrawalQueue.isEmpty()) {
            String request = withdrawalQueue.poll();
            String[] parts = request.split(":");
            String accountNumber = parts[0];
            double amount = Double.parseDouble(parts[1]);

            if (accounts.containsKey(accountNumber) && accounts.get(accountNumber) >= amount) {
                accounts.put(accountNumber, accounts.get(accountNumber) - amount);
                sortedAccounts.put(accountNumber, accounts.get(accountNumber));
                System.out.println("Processed withdrawal of $" + amount + " from " + accountNumber);
            } else {
                System.out.println("Failed to process withdrawal of $" + amount + " from " + accountNumber);
            }
        }
    }

    public Map<String, Double> getAccountsSortedByBalance() {
        Map<String, Double> sortedByBalance = new LinkedHashMap<>();
        accounts.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEachOrdered(entry -> sortedByBalance.put(entry.getKey(), entry.getValue()));
        return sortedByBalance;
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        bank.createAccount("A1001", 5000.0);
        bank.createAccount("A1002", 2500.0);
        bank.createAccount("A1003", 10000.0);

        bank.requestWithdrawal("A1001", 1000.0);
        bank.requestWithdrawal("A1002", 3000.0);
        bank.requestWithdrawal("A1003", 5000.0);

        System.out.println("Accounts sorted by number: " + bank.sortedAccounts);
        System.out.println("Accounts sorted by balance: " + bank.getAccountsSortedByBalance());

        bank.processWithdrawals();

        System.out.println("Accounts after withdrawals: " + bank.accounts);
    }
}