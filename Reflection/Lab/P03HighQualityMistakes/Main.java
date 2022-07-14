package com.company.Reflection.Lab.P03HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class reflection = Reflection.class;

        // Fields
        Field[] fields = reflection.getDeclaredFields();
        Arrays.stream(fields)
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.printf("%s must be private!%n", f.getName()));

        //Getters
        Method[] getters = reflection.getDeclaredMethods();
        Arrays.stream(getters)
                .filter(g -> g.getName().startsWith("get") && !Modifier.isPublic(g.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(g -> System.out.printf("%s have to be public!%n", g.getName()));

        //Setters
        Method[] setters = reflection.getDeclaredMethods();
        Arrays.stream(setters)
                .filter(g -> g.getName().startsWith("set") && !Modifier.isPrivate(g.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(g -> System.out.printf("%s have to be private!%n", g.getName()));
    }
}
