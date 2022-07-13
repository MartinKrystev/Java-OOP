package p02_OpenClosedPrinciple.p03_ShoppingCart.Items;

public class Each extends Special.OrderItem {
    public double getItemPrice(){
        return this.getQuantity() * 4.0 / 1000;
    }
}
