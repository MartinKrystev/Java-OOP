import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            Animal animal = createAnimal(input);
            animals.add(animal);

            Food food = createFood(scanner);

            animal.makeSound();

            try {
                animal.eat(food);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            input = scanner.nextLine();
        }

        animals.forEach(System.out::println);
    }

    private static Food createFood(Scanner scanner) {
        String[] foodInfo = scanner.nextLine().split("\\s+");

        String foodType = foodInfo[0];
        Integer foodQuantity = Integer.parseInt(foodInfo[1]);

        if (foodType.equals("Meat")) {
            return new Meat(foodQuantity);
        } else {
            return new Vegetable(foodQuantity);
        }
    }

    public static Animal createAnimal(String input) {
        String[] animalInfo = input.split("\\s+");

        String animalType = animalInfo[0];
        String animalName = animalInfo[1];
        Double animalWeight = Double.parseDouble(animalInfo[2]);
        String animalLivingRegion = animalInfo[3];

        switch (animalType) {
            case "Mouse":
                return new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
            case "Zebra":
                return new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
            case "Cat":
                String catBreed = animalInfo[4];
                return new Cat(animalName, animalType, animalWeight, animalLivingRegion, catBreed);
            case "Tiger":
                return new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
            default:
                throw new IllegalArgumentException("The universe is cracking... such a creature does NOT exist!");
        }
    }
}
