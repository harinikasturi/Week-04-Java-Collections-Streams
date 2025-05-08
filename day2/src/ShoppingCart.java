import java.util.*;

public class ShoppingCart {
    private Map<String, Double> productPrices = new HashMap<>();
    private Map<String, Integer> cartItems = new LinkedHashMap<>();
    private Map<String, Double> sortedProducts = new TreeMap<>();

    public void addProduct(String name, double price) {
        productPrices.put(name, price);
        sortedProducts.put(name, price);
    }

    public void addToCart(String product, int quantity) {
        if (!productPrices.containsKey(product)) {
            throw new IllegalArgumentException("Product not found");
        }
        cartItems.merge(product, quantity, Integer::sum);
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            total += productPrices.get(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    public Map<String, Double> getProductsByPrice() {
        return new TreeMap<>(productPrices);
    }

    public Map<String, Integer> getCartItems() {
        return new LinkedHashMap<>(cartItems);
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 999.99);
        cart.addProduct("Phone", 699.99);
        cart.addProduct("Headphones", 149.99);

        cart.addToCart("Laptop", 1);
        cart.addToCart("Phone", 2);
        cart.addToCart("Headphones", 1);

        System.out.println("Products sorted by price: " + cart.getProductsByPrice());
        System.out.println("Cart items: " + cart.getCartItems());
        System.out.println("Total: $" + cart.calculateTotal());
    }
}