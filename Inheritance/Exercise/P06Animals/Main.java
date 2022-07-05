package com.company.Inheritance.Exercise.P06Animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equals("Beast!")) {

            String creature = scanner.nextLine();
            try {
                Animal animal = formTheCreature(creature, input);
                System.out.println(animal);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            input = scanner.nextLine();
        }

    }

    private static Animal formTheCreature(String creature, String input) {
        String[] creatureData = creature.split("\\s+");

        String name = creatureData[0];
        int age = Integer.parseInt(creatureData[1]);
        String gender = creatureData[2];

        switch (input) {
            case "Dog":
                return new Dog(name, age, gender);
            case "Cat":
                return new Cat(name, age, gender);
            case "Frog":
                return new Frog(name, age, gender);
            case "Kitten":
                return new Kitten(name, age);
            case "Tomcat":
                return new Tomcat(name, age);
            default:
                throw new IllegalArgumentException("Invalid input!");
        }
    }

}
