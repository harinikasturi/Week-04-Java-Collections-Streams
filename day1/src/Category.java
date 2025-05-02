
import java.util.ArrayList;
import java.util.List;
public interface Category {
    String getCategoryName();
}


enum BookCategory implements Category {
    FICTION, NON_FICTION, SCIENCE, HISTORY;

    @Override
    public String getCategoryName() {
        return this.name();
    }
}

enum ClothingCategory implements Category {
    MEN, WOMEN, KIDS, UNISEX;

    @Override
    public String getCategoryName() {
        return this.name();
    }
}

class Product<T extends Category> {
    private String id;
    private String name;
    private double price;
    private T category;

    public Product(String id, String name, double price, T category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public T getCategory() { return category; }

    public void displayDetails() {
        System.out.println("Product - ID: " + id + ", Name: " + name +
                ", Price: $" + price + ", Category: " + category.getCategoryName());
    }
}




class ProductCatalog {
    private List<? extends Product<?>> products = new ArrayList<>();

    public <T extends Category> void addProduct(Product<T> product) {
        ((List<Product<T>>) products).add(product);
    }

    public <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double currentPrice = product.getPrice();
        double discountedPrice = currentPrice * (1 - percentage / 100);
        System.out.println("Applying " + percentage + "% discount to " + product.getName() +
                ". Old price: $" + currentPrice + ", New price: $" + discountedPrice);
    }

    public void displayAllProducts() {
        for (Product<?> product : products) {
            product.displayDetails();
        }
    }
}

class Main1 {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();

        Product<BookCategory> book = new Product<>("B1", "Java Programming", 49.99, BookCategory.SCIENCE);
        Product<ClothingCategory> shirt = new Product<>("C1", "T-Shirt", 19.99, ClothingCategory.MEN);

        catalog.addProduct(book);
        catalog.addProduct(shirt);

        catalog.displayAllProducts();

        catalog.applyDiscount(book, 10);
        catalog.applyDiscount(shirt, 15);
    }
}