public class Car extends Vehicle {

    private static final double AC_ADDITIONAL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double capacity) {
        super(fuelQuantity, fuelConsumption, capacity);
    }

    @Override
    protected double getAC() {
        return AC_ADDITIONAL_CONSUMPTION;
    }

    @Override
    public boolean refuel(double addedFuel) {
        if (this.getFuelQuantity() + addedFuel <= super.getCapacity()) {
            this.setFuelQuantity(this.getFuelQuantity() + addedFuel);
            return true;
        }
        return false;
    }
}
