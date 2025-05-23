import java.util.*;
import java.util.stream.*;

class InsurancePolicy {
    String policyNumber;
    String holderName;
    double premiumAmount;

    public InsurancePolicy(String policyNumber, String holderName, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.holderName = holderName;
        this.premiumAmount = premiumAmount;
    }

    @Override
    public String toString() {
        return String.format("Policy: %s, Holder: %s, Premium: $%.2f",
                policyNumber, holderName, premiumAmount);
    }
}

class InsuranceApplication {
    public static void main(String[] args) {
        List<InsurancePolicy> policies = Arrays.asList(
                new InsurancePolicy("P1001", "John Smith", 1500.0),
                new InsurancePolicy("P1002", "Alice Johnson", 800.0),
                new InsurancePolicy("P1003", "Bob Brown", 2500.0),
                new InsurancePolicy("P1004", "Anna Smith", 1200.0),
                new InsurancePolicy("P1005", "Charlie Davis", 1800.0)
        );

        // 1. Filter Policies by Premium Amount (> $1200)
        List<InsurancePolicy> highPremiumPolicies = policies.stream()
                .filter(p -> p.premiumAmount > 1200)
                .collect(Collectors.toList());
        System.out.println("\nPolicies with premium > $1200:");
        highPremiumPolicies.forEach(System.out::println);

        // 2. Sort Policies by Holder Name
        List<InsurancePolicy> sortedByName = policies.stream()
                .sorted(Comparator.comparing(p -> p.holderName))
                .collect(Collectors.toList());
        System.out.println("\nPolicies sorted by holder name:");
        sortedByName.forEach(System.out::println);

        // 3. Compute Total Premium
        double totalPremium = policies.stream()
                .mapToDouble(p -> p.premiumAmount)
                .sum();
        System.out.println("\nTotal premium amount: $" + totalPremium);

        // 4. Print Policy Details
        System.out.println("\nAll policy details:");
        policies.forEach(p -> System.out.println(p));

        // 5. Filter Policies by Premium Range ($1000-$2000)
        List<InsurancePolicy> midRangePolicies = policies.stream()
                .filter(p -> p.premiumAmount >= 1000 && p.premiumAmount <= 2000)
                .collect(Collectors.toList());
        System.out.println("\nPolicies with premium between $1000 and $2000:");
        midRangePolicies.forEach(System.out::println);

        // 6. Find Policy with Highest Premium
        Optional<InsurancePolicy> highestPremiumPolicy = policies.stream()
                .max(Comparator.comparingDouble(p -> p.premiumAmount));
        highestPremiumPolicy.ifPresent(p ->
                System.out.println("\nPolicy with highest premium: " + p));

        // 7. Group Policies by Holder Name Initial
        Map<Character, List<InsurancePolicy>> policiesByInitial = policies.stream()
                .collect(Collectors.groupingBy(p -> p.holderName.charAt(0)));
        System.out.println("\nPolicies grouped by holder name initial:");
        policiesByInitial.forEach((initial, plist) -> {
            System.out.println(initial + ":");
            plist.forEach(System.out::println);
        });

        // 8. Compute Average Premium
        double averagePremium = policies.stream()
                .mapToDouble(p -> p.premiumAmount)
                .average()
                .orElse(0);
        System.out.println("\nAverage premium amount: $" + averagePremium);

        // 9. Sort Policies by Premium and Print
        List<InsurancePolicy> sortedByPremium = policies.stream()
                .sorted(Comparator.comparingDouble(p -> p.premiumAmount))
                .collect(Collectors.toList());
        System.out.println("\nPolicies sorted by premium amount:");
        sortedByPremium.forEach(System.out::println);

        // 10. Check If Any Policy Exceeds $2000
        boolean hasHighPremium = policies.stream()
                .anyMatch(p -> p.premiumAmount > 2000);
        System.out.println("\nAny policy with premium > $2000: " + hasHighPremium);

        // 11. Count Policies for Each Premium Range
        Map<String, Long> premiumRangeCounts = policies.stream()
                .collect(Collectors.groupingBy(p -> {
                    if (p.premiumAmount <= 1000) return "$0-$1000";
                    else if (p.premiumAmount <= 2000) return "$1001-$2000";
                    else return ">$2000";
                }, Collectors.counting()));
        System.out.println("\nPolicy counts by premium range:");
        premiumRangeCounts.forEach((range, count) ->
                System.out.println(range + ": " + count));

        // 12. Extract Unique Holder Names
        List<String> uniqueHolderNames = policies.stream()
                .map(p -> p.holderName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\nUnique holder names:");
        uniqueHolderNames.forEach(System.out::println);

        // 13. Find Policies by Holder Name Substring ("Smith")
        List<InsurancePolicy> smithPolicies = policies.stream()
                .filter(p -> p.holderName.contains("Smith"))
                .collect(Collectors.toList());
        System.out.println("\nPolicies with 'Smith' in holder name:");
        smithPolicies.forEach(System.out::println);

        // 14. Create a Map of Policy Numbers to Premium Amounts
        Map<String, Double> policyPremiumMap = policies.stream()
                .collect(Collectors.toMap(
                        p -> p.policyNumber,
                        p -> p.premiumAmount
                ));
        System.out.println("\nPolicy number to premium map:");
        policyPremiumMap.forEach((num, prem) ->
                System.out.println(num + ": $" + prem));
    }
}