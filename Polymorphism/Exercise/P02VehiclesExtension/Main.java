import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]), Double.parseDouble(carInfo[3]));

        String[] truckInfo = scanner.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]), Double.parseDouble(truckInfo[3]));

        String[] busInfo = scanner.nextLine().split("\\s+");
        Vehicle bus = new Bus(Double.parseDouble(busInfo[1]), Double.parseDouble(busInfo[2]), Double.parseDouble(busInfo[3]));

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String[] input = scanner.nextLine().split("\\s+");
            String command = input[0];
            String vehicleType = input[1];
            double amount = Double.parseDouble(input[2]);
            DecimalFormat df = new DecimalFormat("#.##");

            switch (command) {
                case "Drive":
                    if (vehicleType.equals("Car")) {
                        if (car.drive(amount)) {
                            System.out.printf("Car travelled %s km%n", df.format(amount));
                        } else {
                            System.out.println("Car needs refueling");
                        }
                    } else if (vehicleType.equals("Truck")) {
                        if (truck.drive(amount)) {
                            System.out.printf("Truck travelled %s km%n", df.format(amount));
                        } else {
                            System.out.println("Truck needs refueling");
                        }
                    } else if (vehicleType.equals("Bus")) {
                        if (bus.drive(amount)) {
                            System.out.printf("Bus travelled %s km%n", df.format(amount));
                        } else {
                            System.out.println("Bus needs refueling");
                        }
                    }
                    break;
                case "Refuel":
                    if (amount <= 0) {
                        System.out.println("Fuel must be a positive number");
                    } else {
                        if (vehicleType.equals("Car")) {
                            if (!car.refuel(amount)) {
                                System.out.println("Cannot fit fuel in tank");
                            }
                        } else if (vehicleType.equals("Truck")) {
                            if (!truck.refuel(amount)) {
                                System.out.println("Cannot fit fuel in tank");
                            }
                        } else if (vehicleType.equals("Bus")) {
                            if (!bus.refuel(amount)) {
                                System.out.println("Cannot fit fuel in tank");
                            }
                        }
                    }
                    break;
                case "DriveEmpty":
                    bus.setFuelConsumption(bus.getFuelConsumption() - 1.4);
                    if (bus.drive(amount)) {
                        System.out.printf("Bus travelled %s km%n", df.format(amount));
                    } else {
                        System.out.println("Bus needs refueling");
                    }
                    break;
            }
        }

        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n", truck.getFuelQuantity());
        System.out.printf("Bus: %.2f%n", bus.getFuelQuantity());

    }
}
