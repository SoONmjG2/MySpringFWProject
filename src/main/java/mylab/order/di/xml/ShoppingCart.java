package mylab.order.di.xml;

import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {}

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public double getTotalPrice() {
        double total = 0.0;
        if (products != null) {
            for (Product p : products) total += p.getPrice();
        }
        return total;
    }
}
