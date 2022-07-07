package com.company.InterfacesAndAbstraction.Exercise.P04FoodShortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Citizen> citizens = new ArrayList<>();
        List<Rebel> rebels = new ArrayList<>();

        // reaching and adding people to their lists
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");

            if (input.length == 4) {
                Citizen citizen = new Citizen(input[0], Integer.parseInt(input[1]), input[2], input[3]);
                citizens.add(citizen);
            } else if (input.length == 3) {
                Rebel rebel = new Rebel(input[0], Integer.parseInt(input[1]), input[2]);
                rebels.add(rebel);
            }

        }

        // buying Food -> Citizens & Rebels
        String nameInput = scanner.nextLine();
        while (!nameInput.equals("End")) {

            String name = nameInput;
            citizens.stream()
                    .filter(c -> c.getName().equals(name))
                    .findFirst()
                    .ifPresent(Citizen::buyFood);

            rebels.stream()
                    .filter(r -> r.getName().equals(name))
                    .findFirst()
                    .ifPresent(Rebel::buyFood);

            nameInput = scanner.nextLine();
        }

        // print all the summed Food
        System.out.println(citizens.stream()
                .map(Citizen::getFood)
                .reduce(0, Integer::sum) +
                rebels.stream()
                        .map(Rebel::getFood)
                        .reduce(0, Integer::sum));

    }
}
