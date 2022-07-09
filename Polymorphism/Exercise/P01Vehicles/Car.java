package com.company.Polymorphism.Exercise.P01Vehicles;

public class Car extends Vehicle {

    private static final double AC_ADDITIONAL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    @Override
    protected double getAC() {
        return AC_ADDITIONAL_CONSUMPTION;
    }

    @Override
    public void refuel(double addedFuel) {
        this.setFuelQuantity(this.getFuelQuantity() + addedFuel);
    }
}
