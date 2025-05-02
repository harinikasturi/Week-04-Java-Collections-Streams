
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class WarehouseItem {
    private String id;
    private String name;

    public WarehouseItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public abstract void displayDetails();
}


class Electronics extends WarehouseItem {
    private String manufacturer;

    public Electronics(String id, String name, String manufacturer) {
        super(id, name);
        this.manufacturer = manufacturer;
    }

    @Override
    public void displayDetails() {
        System.out.println("Electronics - ID: " + getId() + ", Name: " + getName() + ", Manufacturer: " + manufacturer);
    }
}

class Groceries extends WarehouseItem {
    private String expiryDate;

    public Groceries(String id, String name, String expiryDate) {
        super(id, name);
        this.expiryDate = expiryDate;
    }

    @Override
    public void displayDetails() {
        System.out.println("Groceries - ID: " + getId() + ", Name: " + getName() + ", Expiry Date: " + expiryDate);
    }
}

class Storage<T extends WarehouseItem> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public T getItem(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public static void displayAllItems(List<? extends WarehouseItem> items) {
        for (WarehouseItem item : items) {
            item.displayDetails();
        }
    }

    public Collection<? extends WarehouseItem> getItems() {
        Collection<? extends WarehouseItem> warehouseItems = null;
        return warehouseItems;
    }
}

class Main {
    public static void main(String[] args) {
        Storage<Electronics> electronicsStorage = new Storage<>();
        electronicsStorage.addItem(new Electronics("E1", "Laptop", "Dell"));
        electronicsStorage.addItem(new Electronics("E2", "Smartphone", "Samsung"));

        Storage<Groceries> groceriesStorage = new Storage<>();
        groceriesStorage.addItem(new Groceries("G1", "Milk", "2023-12-31"));
        groceriesStorage.addItem(new Groceries("G2", "Bread", "2023-11-15"));

        List<WarehouseItem> allItems = new ArrayList<>();
        allItems.addAll(electronicsStorage.getItems());
        allItems.addAll(groceriesStorage.getItems());

        Storage.displayAllItems(allItems);
    }
}