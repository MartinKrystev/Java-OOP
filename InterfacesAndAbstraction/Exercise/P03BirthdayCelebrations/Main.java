package com.company.InterfacesAndAbstraction.Exercise.P03BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> list = new ArrayList<>();

        String input = scanner.nextLine();
        while (!input.equals("End")) {
            String[] data = input.split("\\s+");

            switch (data[0]) {
                case "com.company.InterfacesAndAbstraction.Exercise.P03BirthdayCelebrations.Citizen":
                    Citizen citizen = new Citizen(data[1], Integer.parseInt(data[2]), data[3], data[4]);
                    list.add(data[4]);
                    break;
                case "com.company.InterfacesAndAbstraction.Exercise.P03BirthdayCelebrations.Robot":
                    // nothing happens
                    break;
                case "com.company.InterfacesAndAbstraction.Exercise.P03BirthdayCelebrations.Pet":
                    Pet pet = new Pet(data[1], data[2]);
                    list.add(data[2]);
                    break;
            }

            input = scanner.nextLine();
        }

        String searchedYear = scanner.nextLine();

        List<String> matchedYears = new ArrayList<>();
        list.forEach(e -> {
            if (e.endsWith(searchedYear)) {
                matchedYears.add(e);
            }
        });

        if (matchedYears.isEmpty()) {
            System.out.println("<no output>");
        } else {
            matchedYears.forEach(System.out::println);
        }

    }
}
