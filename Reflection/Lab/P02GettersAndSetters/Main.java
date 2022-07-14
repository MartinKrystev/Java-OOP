package com.company.Reflection.Lab.P02GettersAndSetters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class reflection = Reflection.class;

        Method[] methods = reflection.getDeclaredMethods();
        Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("get") && m.getParameterCount() == 0)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.printf("%s will return class %s%n", m.getName(), m.getReturnType().getName()));

        Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("set"))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.printf("%s and will set field of class %s%n"
                        , m.getName(), m.getParameterTypes()[0].getName()));

    }
}
