package com.company.Reflection.Exercise.P01HarvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class sample = RichSoilLand.class;
        Field[] fields = sample.getDeclaredFields();

        String input = scanner.nextLine();
        while (!input.equals("HARVEST")) {
            switch (input) {
                case "private":
                    printPrivateFields(fields);
                    break;
                case "protected":
                    printProtectedFields(fields);
                    break;
                case "public":
                    printPublicFields(fields);
                    break;
                case "all":
                    printAllFields(fields);
                    break;
            }

            input = scanner.nextLine();
        }

    }

    private static void printAllFields(Field[] fields) {
        Arrays.stream(fields)
                .forEach(f -> System.out.printf("%s %s %s%n",Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
    }

    private static void printPublicFields(Field[] fields) {
        Arrays.stream(fields)
                .filter(f -> Modifier.isPublic(f.getModifiers()))
                .forEach(f -> System.out.printf("%s %s %s%n",Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
    }

    private static void printProtectedFields(Field[] fields) {
        Arrays.stream(fields)
                .filter(f -> Modifier.isProtected(f.getModifiers()))
                .forEach(f -> System.out.printf("%s %s %s%n",Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
    }

    private static void printPrivateFields(Field[] fields) {
        Arrays.stream(fields)
                .filter(f -> Modifier.isPrivate(f.getModifiers()))
                .forEach(f -> System.out.printf("%s %s %s%n",Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
    }
}
