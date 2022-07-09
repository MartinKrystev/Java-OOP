package com.company.Polymorphism.Exercise.P01Vehicles;

public class Truck extends Vehicle {

    private static final double AC_ADDITIONAL_CONSUMPTION = 1.6;
    private static final double TANK_HOLE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    @Override
    protected double getAC() {
        return AC_ADDITIONAL_CONSUMPTION;
    }

    @Override
    public void refuel(double addedFuel) {
        this.setFuelQuantity(this.getFuelQuantity() + addedFuel * TANK_HOLE );
    }
}
