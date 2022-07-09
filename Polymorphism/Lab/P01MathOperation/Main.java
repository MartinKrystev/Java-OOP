package com.company.Polymorphism.Lab.P01MathOperation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MathOperation math = new MathOperation();
        System.out.println(math.add(2, 3));
        System.out.println(math.add(2, 3, 5));
        System.out.println(math.add(2, 3, 5, 5));

    }
}
