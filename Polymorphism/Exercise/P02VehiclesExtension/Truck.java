public class Truck extends Vehicle {

    private static final double AC_ADDITIONAL_CONSUMPTION = 1.6;
    private static final double TANK_HOLE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double capacity) {
        super(fuelQuantity, fuelConsumption, capacity);
    }

    @Override
    protected double getAC() {
        return AC_ADDITIONAL_CONSUMPTION;
    }

    @Override
    public boolean refuel(double addedFuel) {
        if (this.getFuelQuantity() + addedFuel * TANK_HOLE <= super.getCapacity()) {
            this.setFuelQuantity(this.getFuelQuantity() + addedFuel * TANK_HOLE);
            return true;
        }
        return false;
    }
}
