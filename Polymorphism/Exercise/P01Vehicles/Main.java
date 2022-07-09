package com.company.Polymorphism.Exercise.P01Vehicles;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));

        String[] truckInfo = scanner.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String[] input = scanner.nextLine().split("\\s+");
            String command = input[0];
            String vehicleType = input[1];
            double amount = Double.parseDouble(input[2]);
            DecimalFormat df = new DecimalFormat("#.##");

            switch (command) {
                case "Drive":
                    if (vehicleType.equals("com.company.Polymorphism.Exercise.P01Vehicles.Car")) {
                        if (car.drive(amount)) {
                            System.out.printf("com.company.Polymorphism.Exercise.P01Vehicles.Car travelled %s km%n", df.format(amount));
                        } else {
                            System.out.println("com.company.Polymorphism.Exercise.P01Vehicles.Car needs refueling");
                        }
                    } else if (vehicleType.equals("com.company.Polymorphism.Exercise.P01Vehicles.Truck")) {
                        if (truck.drive(amount)) {
                            System.out.printf("com.company.Polymorphism.Exercise.P01Vehicles.Truck travelled %s km%n", df.format(amount));
                        } else {
                            System.out.println("com.company.Polymorphism.Exercise.P01Vehicles.Truck needs refueling");
                        }
                    }
                    break;
                case "Refuel":
                        if (vehicleType.equals("com.company.Polymorphism.Exercise.P01Vehicles.Car")) {
                            car.refuel(amount);
                        } else if (vehicleType.equals("com.company.Polymorphism.Exercise.P01Vehicles.Truck")) {
                            truck.refuel(amount);
                        }
                    break;
            }
        }

        System.out.printf("com.company.Polymorphism.Exercise.P01Vehicles.Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("com.company.Polymorphism.Exercise.P01Vehicles.Truck: %.2f", truck.getFuelQuantity());

    }
}
