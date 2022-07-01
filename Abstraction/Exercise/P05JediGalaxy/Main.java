package com.company.WorkingWithAbstraction.Exercise.P05JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        int value = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value++;
            }
        }

        String command = scanner.nextLine();

        long sum = 0;

        while (!command.equals("Let the Force be with you")) {
            int[] heroCoordinates = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinates = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            int rowEvil = evilCoordinates[0];
            int colEvil = evilCoordinates[1];

            moveEvil(matrix, rowEvil, colEvil);

            int rowHero = heroCoordinates[0];
            int colHero = heroCoordinates[1];

            sum = moveHero(matrix, sum, rowHero, colHero);

            command = scanner.nextLine();
        }

        System.out.println(sum);
    }

    private static long moveHero(int[][] matrix, long sum, int rowHero, int colHero) {
        while (rowHero >= 0 && colHero < matrix[1].length) {
            if (rowHero >= 0 && rowHero < matrix.length && colHero >= 0 && colHero < matrix[0].length) {
                sum += matrix[rowHero][colHero];
            }

            colHero++;
            rowHero--;
        }
        return sum;
    }

    private static void moveEvil(int[][] matrix, int rowEvil, int colEvil) {
        while (rowEvil >= 0 && colEvil >= 0) {
            if (rowEvil >= 0 && rowEvil < matrix.length && colEvil >= 0 && colEvil < matrix[0].length) {
                matrix[rowEvil][colEvil] = 0;
            }
            rowEvil--;
            colEvil--;
        }
    }
}
