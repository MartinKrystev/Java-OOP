package com.company.Encapsulation.Exercies.P04PizzaCalories;

public class Dough {

    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        if (!flourType.equals("White") && !flourType.equals("Wholegrain")) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!bakingTechnique.equals("Crispy") && !bakingTechnique.equals("Chewy") && !bakingTechnique.equals("Homemade")) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories () {
        if (this.flourType.equals("White")) {
            if (this.bakingTechnique.equals("Crispy")) {
                return (this.weight * 2) * 1.5 * 0.9;
            } else if (this.bakingTechnique.equals("Chewy")) {
                return (this.weight * 2) * 1.5 * 1.1;
            } else if (this.bakingTechnique.equals("Homemade")) {
                return (this.weight * 2) * 1.5 * 1.0;
            } else {
                return -1;
            }
        } else if (this.flourType.equals("Wholegrain")) {
            if (this.bakingTechnique.equals("Crispy")) {
                return (this.weight * 2) * 1.0 * 0.9;
            } else if (this.bakingTechnique.equals("Chewy")) {
                return (this.weight * 2) * 1.0 * 1.1;
            } else if (this.bakingTechnique.equals("Homemade")) {
                return (this.weight * 2) * 1.0 * 1.0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }


}
