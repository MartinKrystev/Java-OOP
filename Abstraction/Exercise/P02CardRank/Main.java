package com.company.WorkingWithAbstraction.Exercise.P02CardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Card Ranks:");

        CardRanks[] cardRanks = CardRanks.values();

        for (CardRanks cardRank : cardRanks) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",cardRank.ordinal(), cardRank);
        }
    }
}
