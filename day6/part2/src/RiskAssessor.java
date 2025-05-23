import java.util.*;
import java.util.stream.Collectors;

public class RiskAssessor {

    public static Map<String, List<RiskAssessment>> assessPolicyHolders(List<PolicyHolder> holders) {
        // Validate input
        if (holders == null) {
            throw new IllegalArgumentException("Policy holders list cannot be null");
        }

        return holders.stream()
                .filter(Objects::nonNull)
                .filter(h -> "Life".equalsIgnoreCase(h.getPolicyType()))
                .filter(h -> h.getAge() > 60)
                .map(h -> {
                    double riskScore = h.getPremiumAmount() / h.getAge();
                    String riskCategory = riskScore > 0.5 ? "High Risk" : "Low Risk";
                    return new RiskAssessment(
                            h.getHolderId(),
                            h.getName(),
                            riskScore,
                            riskCategory
                    );
                })
                .sorted(Comparator.comparingDouble(RiskAssessment::getRiskScore).reversed())
                .collect(Collectors.groupingBy(
                        RiskAssessment::getRiskCategory,
                        Collectors.toList()
                ));
    }
}

class PolicyHolder {
    private final String holderId;
    private final String name;
    private final int age;
    private final String policyType;
    private final double premiumAmount;

    public PolicyHolder(String holderId, String name, int age,
                        String policyType, double premiumAmount) {
        this.holderId = validateString(holderId, "Holder ID");
        this.name = validateString(name, "Name");
        this.age = validateAge(age);
        this.policyType = validateString(policyType, "Policy Type");
        this.premiumAmount = validateAmount(premiumAmount);
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return value.trim();
    }

    private int validateAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
        return age;
    }

    private double validateAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Premium amount cannot be negative");
        }
        return amount;
    }

    // Getters
    public String getHolderId() { return holderId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPolicyType() { return policyType; }
    public double getPremiumAmount() { return premiumAmount; }
}

class RiskAssessment {
    private final String holderId;
    private final String name;
    private final double riskScore;
    private final String riskCategory;

    public RiskAssessment(String holderId, String name,
                          double riskScore, String riskCategory) {
        this.holderId = holderId;
        this.name = name;
        this.riskScore = riskScore;
        this.riskCategory = riskCategory;
    }

    // Getters
    public String getHolderId() { return holderId; }
    public String getName() { return name; }
    public double getRiskScore() { return riskScore; }
    public String getRiskCategory() { return riskCategory; }

    @Override
    public String toString() {
        return String.format("%s (%s): Risk=%.2f (%s)",
                name, holderId, riskScore, riskCategory);
    }
}