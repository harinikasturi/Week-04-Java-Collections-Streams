import java.util.*;
import java.util.stream.Collectors;

public class ClaimsAnalyzer {

    public static List<PolicySummary> analyzeClaims(List<Claim> claims) {
        // Validate input
        if (claims == null) {
            throw new IllegalArgumentException("Claims list cannot be null");
        }

        return claims.stream()
                .filter(Objects::nonNull)
                .filter(c -> "Approved".equalsIgnoreCase(c.getStatus()))
                .filter(c -> c.getClaimAmount() > 5000)
                .collect(Collectors.groupingBy(
                        Claim::getPolicyNumber,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                policyClaims -> {
                                    double total = policyClaims.stream()
                                            .mapToDouble(Claim::getClaimAmount)
                                            .sum();
                                    double average = total / policyClaims.size();
                                    return new PolicySummary(
                                            policyClaims.get(0).getPolicyNumber(),
                                            total,
                                            average,
                                            policyClaims.size()
                                    );
                                }
                        )
                ))
                .values().stream()
                .sorted(Comparator.comparingDouble(PolicySummary::getTotalAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}

class Claim {
    private final String claimId;
    private final String policyNumber;
    private final double claimAmount;
    private final Date claimDate;
    private final String status;

    public Claim(String claimId, String policyNumber, double claimAmount,
                 Date claimDate, String status) {
        this.claimId = validateString(claimId, "Claim ID");
        this.policyNumber = validateString(policyNumber, "Policy Number");
        this.claimAmount = validateAmount(claimAmount);
        this.claimDate = Objects.requireNonNull(claimDate, "Claim date cannot be null");
        this.status = validateString(status, "Status");
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return value.trim();
    }

    private double validateAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Claim amount cannot be negative");
        }
        return amount;
    }

    // Getters
    public String getClaimId() { return claimId; }
    public String getPolicyNumber() { return policyNumber; }
    public double getClaimAmount() { return claimAmount; }
    public Date getClaimDate() { return claimDate; }
    public String getStatus() { return status; }
}

class PolicySummary {
    private final String policyNumber;
    private final double totalAmount;
    private final double averageAmount;
    private final int claimCount;

    public PolicySummary(String policyNumber, double totalAmount,
                         double averageAmount, int claimCount) {
        this.policyNumber = policyNumber;
        this.totalAmount = totalAmount;
        this.averageAmount = averageAmount;
        this.claimCount = claimCount;
    }

    // Getters
    public String getPolicyNumber() { return policyNumber; }
    public double getTotalAmount() { return totalAmount; }
    public double getAverageAmount() { return averageAmount; }
    public int getClaimCount() { return claimCount; }

    @Override
    public String toString() {
        return String.format("Policy %s: Total=%.2f, Avg=%.2f, Count=%d",
                policyNumber, totalAmount, averageAmount, claimCount);
    }
}