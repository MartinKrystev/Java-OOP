package com.company.Encapsulation.Exercies.P04PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // read Pizza input and create Object
        String[] pizzaInput = scanner.nextLine().split("\\s+");
        String pizzaName = pizzaInput[1];
        int numberOfToppings = Integer.parseInt(pizzaInput[2]);

        try {
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);

            // read Dough input and create Object
            String[] doughInput = scanner.nextLine().split("\\s+");
            String flourType = doughInput[1];
            String bakingTechnique = doughInput[2];
            double weightInGrams = Double.parseDouble(doughInput[3]);

            try {
                Dough dough = new Dough(flourType, bakingTechnique, weightInGrams);
                pizza.setDough(dough);

                // Read Toppings input and create Object
                String input = scanner.nextLine();
                while (!input.equals("END")) {
                    String toppingType = input.split("\\s+")[1];
                    double currWeight = Double.parseDouble(input.split("\\s+")[2]);

                    try {
                        Topping topping = new Topping(toppingType, currWeight);
                        pizza.addTopping(topping);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        return;
                    }

                    input = scanner.nextLine();
                }

                // Print result
                System.out.printf("%s - %.2f", pizzaName, pizza.getOverallCalories());

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
