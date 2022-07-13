package p02_OpenClosedPrinciple.p03_ShoppingCart.Items;

public class Weight extends Special.OrderItem {
    public double getItemPrice(){
        return this.getQuantity() * 5.0;
    }

}
