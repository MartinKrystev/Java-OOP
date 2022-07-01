package com.company.WorkingWithAbstraction.Exercise.P06GreedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long capacity = Long.parseLong(scanner.nextLine());

        String[] items = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(capacity);

        for (int i = 0; i < items.length; i += 2) {
            String item = items[i];
            long quantity = Long.parseLong(items[i + 1]);

            if (item.length() == 3) {
                bag.addCash(item, quantity);
            } else if (item.toLowerCase().endsWith("gem") && item.length() > 3) {
                bag.addGems(item, quantity);
            } else if (item.equals("Gold")) {
                bag.addGold(quantity);
            }

        }

        System.out.println(bag);
    }
}
