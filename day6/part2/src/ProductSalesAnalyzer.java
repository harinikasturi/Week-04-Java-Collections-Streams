import java.util.*;
import java.util.stream.Collectors;

public class ProductSalesAnalyzer {

    public static List<ProductSales> analyzeSales(List<Sale> sales) {
        if (sales == null) {
            return Collections.emptyList();
        }

        return sales.stream()
                .filter(Objects::nonNull)  // Filter out null Sale objects
                .filter(s -> s.getQuantity() > 10)
                .collect(Collectors.toMap(
                        Sale::getProductId,
                        s -> s.getQuantity() * s.getPrice(),
                        Double::sum))  // Merge duplicates by summing revenues
                .entrySet().stream()
                .map(entry -> new ProductSales(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingDouble(ProductSales::getTotalRevenue).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // Alternatively, if you want to keep the original approach but make it more robust:
    public static List<ProductSales> analyzeSalesAlternative(List<Sale> sales) {
        if (sales == null || sales.isEmpty()) {
            return Collections.emptyList();
        }

        return sales.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getQuantity() > 10)
                .map(s -> new ProductSales(s.getProductId(), s.getQuantity() * s.getPrice()))
                .collect(Collectors.groupingBy(
                        ProductSales::getProductId,
                        Collectors.summingDouble(ProductSales::getTotalRevenue)))
                .entrySet().stream()
                .map(entry -> new ProductSales(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingDouble(ProductSales::getTotalRevenue).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
}

// Sale class with proper encapsulation
class Sale {
    private final String productId;
    private final int quantity;
    private final double price;

    public Sale(String productId, int quantity, double price) {
        this.productId = Objects.requireNonNull(productId);
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}

// Immutable ProductSales class
class ProductSales {
    private final String productId;
    private final double totalRevenue;

    public ProductSales(String productId, double totalRevenue) {
        this.productId = Objects.requireNonNull(productId);
        this.totalRevenue = totalRevenue;
    }

    public String getProductId() { return productId; }
    public double getTotalRevenue() { return totalRevenue; }

    @Override
    public String toString() {
        return String.format("Product: %s, Revenue: %.2f", productId, totalRevenue);
    }
}