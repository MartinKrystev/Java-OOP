public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double capacity;

    public Vehicle(double fuelQuantity, double fuelConsumption,double capacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
        this.setCapacity(capacity);
    }

    public double getCapacity() {
        return capacity;
    }

    protected void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption + getAC();
    }

    protected abstract double getAC();

    public boolean drive(double distance) {
        double neededFuel = distance * this.fuelConsumption;

        if (neededFuel <= this.fuelQuantity) {
            this.fuelQuantity -= neededFuel;
            return true;
        }
        return false;
    }

    public abstract boolean refuel(double addedFuel);


}
