package com.company.Reflection.Exercise.P02BlackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class clazz = BlackBoxInt.class;

        BlackBoxInt instance = getNewInstance(clazz);

        Field field = getField(clazz);

        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String command = input.split("_")[0];
            int value = Integer.parseInt(input.split("_")[1]);

            applyMethod(clazz, instance, command, value);

            System.out.println(field.get(instance));

            input = scanner.nextLine();
        }


    }

    private static void applyMethod(Class clazz, BlackBoxInt instance, String command, int value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method[] methods = clazz.getDeclaredMethods();
        Method method = clazz.getDeclaredMethod(command, int.class);
        method.setAccessible(true);
        method.invoke(instance, value);
    }

    private static BlackBoxInt getNewInstance(Class clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor[] constructor = clazz.getDeclaredConstructors();
        Constructor intConstructor = clazz.getDeclaredConstructor(int.class);
        intConstructor.setAccessible(true);
        BlackBoxInt testInstance = (BlackBoxInt) intConstructor.newInstance(0);
        return testInstance;
    }

    private static Field getField(Class clazz) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField("innerValue");
        field.setAccessible(true);
        return field;
    }
}
