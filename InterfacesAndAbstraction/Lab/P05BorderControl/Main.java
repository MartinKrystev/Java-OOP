package com.company.InterfacesAndAbstraction.Lab.P05BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> list = new ArrayList<>();

        String input = scanner.nextLine();
        while (!input.equals("End")) {
            String[] data = input.split("\\s+");

            if (data.length == 2) {
                Robot robot = new Robot(data[0], data[1]);
                list.add(data[1]);
            } else if (data.length == 3) {
                Citizen citizen = new Citizen(data[0], Integer.parseInt(data[1]), data[2]);
                list.add(data[2]);
            }

            input = scanner.nextLine();
        }

        String fakeId = scanner.nextLine();

        for (String s : list) {
            if (s.endsWith(fakeId)) {
                System.out.println(s);
            }
        }


    }
}
