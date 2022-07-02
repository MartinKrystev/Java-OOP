package com.company.Encapsulation.Exercies.P02AnimalFarm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String chickenName = scanner.nextLine();
        int chickenAge = Integer.parseInt(scanner.nextLine());

        Chicken chicken;

        try {
            chicken = new Chicken(chickenName, chickenAge);
            System.out.println(chicken);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
