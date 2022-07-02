package com.company.Encapsulation.Exercies.P03ShoppingSpree;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] peopleInput = scanner.nextLine().split(";");
        String[] productsInput = scanner.nextLine().split(";");

        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        // Checks all people
        for (String el : peopleInput) {
            String[] data = el.split("=");
            String name = data[0];
            double money = Double.parseDouble(data[1]);

            try {
                Person person = new Person(name, money);
                people.put(name, person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        // Checks all products
        for (String el : productsInput) {
            String[] data = el.split("=");
            String name = data[0];
            double cost = Double.parseDouble(data[1]);

            try {
                Product product = new Product(name, cost);
                products.put(name, product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        // Shopping
        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String[] command = input.split("\\s+");
            String personName = command[0];
            String productName = command[1];

            try {
                people.get(personName).buyProduct(products.get(productName));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            input = scanner.nextLine();
        }

        // Print people and their products
        for (Person p : people.values()) {
            System.out.print(p.getName() + " - ");
            if (p.getProducts().isEmpty()) {
                System.out.println("Nothing bought");
            } else {
                System.out.println(p.getProducts().stream().map(Product::getName).collect(Collectors.joining(", ")));
            }
        }

    }
}
