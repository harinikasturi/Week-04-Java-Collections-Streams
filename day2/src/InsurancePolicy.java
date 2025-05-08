import java.util.*;
import java.time.LocalDate;

class InsurancePolicy {
    private String policyNumber;
    private String policyholderName;
    private LocalDate expiryDate;
    private String coverageType;
    private double premiumAmount;

    public InsurancePolicy(String policyNumber, String policyholderName,
                           LocalDate expiryDate, String coverageType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    // Getters and setters
    public String getPolicyNumber() { return policyNumber; }
    public String getPolicyholderName() { return policyholderName; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getCoverageType() { return coverageType; }
    public double getPremiumAmount() { return premiumAmount; }

    @Override
    public String toString() {
        return "Policy{" + policyNumber + ", " + policyholderName + ", " +
                expiryDate + ", " + coverageType + ", $" + premiumAmount + "}";
    }
}

class PolicyManagementSystem {
    private Set<InsurancePolicy> hashSet = new HashSet<>();
    private Set<InsurancePolicy> linkedHashSet = new LinkedHashSet<>();
    private Set<InsurancePolicy> treeSet = new TreeSet<>(Comparator.comparing(InsurancePolicy::getExpiryDate));

    private Map<String, InsurancePolicy> hashMap = new HashMap<>();
    private Map<String, InsurancePolicy> linkedHashMap = new LinkedHashMap<>();
    private Map<String, InsurancePolicy> treeMap = new TreeMap<>();

    public void addPolicy(InsurancePolicy policy) {
        hashSet.add(policy);
        linkedHashSet.add(policy);
        treeSet.add(policy);

        hashMap.put(policy.getPolicyNumber(), policy);
        linkedHashMap.put(policy.getPolicyNumber(), policy);
        treeMap.put(policy.getPolicyNumber(), policy);
    }

    public InsurancePolicy getPolicyByNumber(String policyNumber) {
        return hashMap.get(policyNumber);
    }

    public Set<InsurancePolicy> getPoliciesExpiringSoon() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(30);

        Set<InsurancePolicy> result = new HashSet<>();
        for (InsurancePolicy policy : treeSet) {
            if (policy.getExpiryDate().isAfter(today) && policy.getExpiryDate().isBefore(futureDate)) {
                result.add(policy);
            }
        }
        return result;
    }

    public Set<InsurancePolicy> getPoliciesByCoverageType(String coverageType) {
        Set<InsurancePolicy> result = new HashSet<>();
        for (InsurancePolicy policy : hashSet) {
            if (policy.getCoverageType().equalsIgnoreCase(coverageType)) {
                result.add(policy);
            }
        }
        return result;
    }

    public void removeExpiredPolicies() {
        LocalDate today = LocalDate.now();

        hashSet.removeIf(policy -> policy.getExpiryDate().isBefore(today));
        linkedHashSet.removeIf(policy -> policy.getExpiryDate().isBefore(today));
        treeSet.removeIf(policy -> policy.getExpiryDate().isBefore(today));

        hashMap.values().removeIf(policy -> policy.getExpiryDate().isBefore(today));
        linkedHashMap.values().removeIf(policy -> policy.getExpiryDate().isBefore(today));
        treeMap.values().removeIf(policy -> policy.getExpiryDate().isBefore(today));
    }

    public static void main(String[] args) {
        PolicyManagementSystem system = new PolicyManagementSystem();

        system.addPolicy(new InsurancePolicy("P001", "John Doe",
                LocalDate.of(2023, 12, 31), "Auto", 500.0));
        system.addPolicy(new InsurancePolicy("P002", "Jane Smith",
                LocalDate.of(2023, 11, 15), "Home", 1200.0));
        system.addPolicy(new InsurancePolicy("P003", "John Doe",
                LocalDate.of(2024, 6, 30), "Health", 800.0));

        System.out.println("Policy P001: " + system.getPolicyByNumber("P001"));
        System.out.println("Policies expiring soon: " + system.getPoliciesExpiringSoon());
        System.out.println("Auto policies: " + system.getPoliciesByCoverageType("Auto"));

        system.removeExpiredPolicies();
    }
}