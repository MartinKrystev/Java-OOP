package com.company.WorkingWithAbstraction.Lab;

import java.util.Scanner;

public class P01RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        printFigure(size);

    }

    private static void printFigure(int size) {
        for (int starCount = 1; starCount <= size; starCount++) {
            printRow(size, starCount);
        }

        for (int starCount = size - 1; starCount >= 1; starCount--) {
            printRow(size, starCount);
        }
    }

    private static void printRow(int size, int starCount) {
        for (int i = 0; i < size - starCount; i++) {
            System.out.print(" ");
        }
        for (int j = 1; j < starCount; j++) {
            System.out.print("* ");
        }

        System.out.println("*");
    }
}
