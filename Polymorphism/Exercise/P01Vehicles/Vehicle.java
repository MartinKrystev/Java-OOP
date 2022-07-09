package com.company.Polymorphism.Exercise.P01Vehicles;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
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

    public abstract void refuel(double addedFuel);
}
