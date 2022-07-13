package p02_OpenClosedPrinciple.p03_ShoppingCart;

import p02_OpenClosedPrinciple.p03_ShoppingCart.Items.Special;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Special.OrderItem> items;

    public String customerEmail;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Iterable<Special.OrderItem> getItems() {
        return new ArrayList<Special.OrderItem>(this.items);
    }


    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void add(Special.OrderItem orderItem) {
        this.items.add(orderItem);
    }

    public double getTotalAmount() {
        double total = 0;

        for(var item : this.items)
        {
            if (item.getBarcode().startsWith("EACH")) {
                total += item.getQuantity() * 5.0;
            } else if (item.getBarcode().startsWith("WEIGHT")) {
                // quantity is in grams, price is per kg
                total += item.getQuantity() * 4.0 / 1000;
            } else if (item.getBarcode().startsWith("SPECIAL")) {
                // $0.40 each; 3 for $1.00
                total += item.getQuantity() * 4.0;
                int setsOfThree = item.getQuantity() / 3;
                total -= setsOfThree * 2.0;
            }

            // more rules are coming!
        }

        return total;
    }
}
