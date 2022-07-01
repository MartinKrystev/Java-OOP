package com.company.WorkingWithAbstraction.Lab.HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] data = scanner.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(data[0]);
        int days = Integer.parseInt(data[1]);
        Season season = Season.parse(data[2]);
        Discount discount = Discount.parse(data[3]);

        PriceCalculator calculator = new PriceCalculator(pricePerDay, days, season, discount);

        System.out.printf("%.2f%n", calculator.calculatePrice());
    }
}
