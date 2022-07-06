package com.company.InterfacesAndAbstraction.Lab.P06Ferrari;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Ferrari ferrari = new Ferrari(input);

        System.out.println(ferrari);

    }
}
