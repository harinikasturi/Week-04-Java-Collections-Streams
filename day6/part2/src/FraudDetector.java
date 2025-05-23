import java.util.*;
import java.util.stream.Collectors;

class FraudDetectionSystem {

    public static List<FraudAlert> detectFraudulentPolicies(List<Transaction> transactions) {
        // Validate input
        if (transactions == null) {
            throw new IllegalArgumentException("Transactions list cannot be null");
        }

        return transactions.stream()
                .filter(Objects::nonNull)  // Remove null transactions
                .filter(Transaction::isFraudulent)  // Only fraudulent transactions
                .filter(t -> t.getAmount() > 10000)  // Above $10,000 threshold
                .collect(Collectors.groupingBy(
                        Transaction::getPolicyNumber,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                fraudTransactions -> {
                                    int transactionCount = fraudTransactions.size();
                                    double totalAmount = fraudTransactions.stream()
                                            .mapToDouble(Transaction::getAmount)
                                            .sum();
                                    boolean needsAlert = transactionCount > 5 || totalAmount > 50000;
                                    return new FraudAlert(
                                            fraudTransactions.get(0).getPolicyNumber(),
                                            transactionCount,
                                            totalAmount,
                                            needsAlert
                                    );
                                }
                        )
                ))
                .values().stream()
                .filter(FraudAlert::isNeedsAlert)  // Only include policies that need alert
                .sorted(Comparator.comparingDouble(FraudAlert::getTotalAmount).reversed())
                .collect(Collectors.toList());
    }
}

class Transaction {
    private final String transactionId;
    private final String policyNumber;
    private final double amount;
    private final Date transactionDate;
    private final boolean isFraudulent;

    public Transaction(String transactionId, String policyNumber, double amount,
                       Date transactionDate, boolean isFraudulent) {
        this.transactionId = validateString(transactionId, "Transaction ID");
        this.policyNumber = validateString(policyNumber, "Policy Number");
        this.amount = validateAmount(amount);
        this.transactionDate = Objects.requireNonNull(transactionDate, "Transaction date cannot be null");
        this.isFraudulent = isFraudulent;
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return value.trim();
    }

    private double validateAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative");
        }
        return amount;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public String getPolicyNumber() { return policyNumber; }
    public double getAmount() { return amount; }
    public Date getTransactionDate() { return transactionDate; }
    public boolean isFraudulent() { return isFraudulent; }
}

class FraudAlert {
    private final String policyNumber;
    private final int fraudCount;
    private final double totalAmount;
    private final boolean needsAlert;

    public FraudAlert(String policyNumber, int fraudCount,
                      double totalAmount, boolean needsAlert) {
        this.policyNumber = policyNumber;
        this.fraudCount = fraudCount;
        this.totalAmount = totalAmount;
        this.needsAlert = needsAlert;
    }

    // Getters
    public String getPolicyNumber() { return policyNumber; }
    public int getFraudCount() { return fraudCount; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isNeedsAlert() { return needsAlert; }

    @Override
    public String toString() {
        return String.format("Policy %s: %d frauds, Total=%.2f, Alert=%s",
                policyNumber, fraudCount, totalAmount, needsAlert ? "YES" : "NO");
    }
}