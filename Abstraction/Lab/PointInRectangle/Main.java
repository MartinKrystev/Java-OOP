package com.company.WorkingWithAbstraction.Lab.PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coordinates = getCoordinates(scanner);

        Point a = new Point(coordinates[0], coordinates[1]);
        Point b = new Point(coordinates[2], coordinates[3]);

        Rectangle rect = new Rectangle(a, b);

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            int[] pointCoordinates = getCoordinates(scanner);

            Point x = new Point(pointCoordinates[0], pointCoordinates[1]);

            System.out.println(rect.contains(x));
        }

    }

    private static int[] getCoordinates(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
