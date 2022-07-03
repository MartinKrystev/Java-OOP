package com.company.Encapsulation.Exercies.P04PizzaCalories;

public class Topping {

    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String s) {
        if (!s.equals("Meat") && !s.equals("Veggies") && !s.equals("Cheese") && !s.equals("Sauce")) {
            String message = String.format("Cannot place %s on top of your pizza.", s);
            throw new IllegalArgumentException(message);
        }
        this.toppingType = s;
    }

    private void setWeight(double d) {
        if (d < 1 || d > 50) {
            String message = String.format("%s weight should be in the range [1..50].", this.toppingType);
            throw new IllegalArgumentException(message);
        }
        this.weight = d;
    }

    public double calculateCalories() {
        if (this.toppingType.equals("Meat")) {
            return (this.weight * 2) * 1.2;
        } else if (this.toppingType.equals("Veggies")) {
            return (this.weight * 2) * 0.8;
        } else if (this.toppingType.equals("Cheese")) {
            return (this.weight * 2) * 1.1;
        } else if (this.toppingType.equals("Sauce")) {
            return (this.weight * 2) * 0.9;
        } else {
            return -1;
        }
    }

}

