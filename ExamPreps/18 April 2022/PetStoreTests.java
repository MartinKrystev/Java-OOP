package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PetStoreTests {
    private PetStore petStore;
    private Animal animal;
    private Animal expensiveAnimal;
    private static final int ANIMAL_COUNT_TWO = 2;

    private static final String ANIMAL_TYPE = "Horse";
    private static final int ANIMAL_WEIGHT = 100;
    private static final double ANIMAL_PRICE = 250.00;

    private static final double ANIMAL_PRICE_EXPENSIVE = 400.00;

    @Before
    public void setup() {
        petStore = new PetStore();
        animal = new Animal(ANIMAL_TYPE, ANIMAL_WEIGHT, ANIMAL_PRICE);
        expensiveAnimal = new Animal("Cat", 50, ANIMAL_PRICE_EXPENSIVE);
        petStore.addAnimal(animal);
        petStore.addAnimal(expensiveAnimal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidAnimal() {
        petStore.addAnimal(null);
    }

    @Test
    public void testGetCorrectAnimalCount() {
        int count = petStore.getCount();
        assertEquals(ANIMAL_COUNT_TWO, count);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testModifyUnmodifiableList() {
        petStore.getAnimals().clear();
    }

    @Test
    public void testFindTheHeaviestAnimal() {
        List<Animal> heaviest = petStore.findAllAnimalsWithMaxKilograms(99);
        assertEquals(animal, heaviest.get(0));
    }

    @Test
    public void testTheMostExpensiveAnimal() {
        Animal theExpensiveOne = petStore.getTheMostExpensiveAnimal();
        assertEquals(expensiveAnimal, theExpensiveOne);
    }

    @Test
    public void testFindingAnimalBySpecie(){
        List<Animal> bySpecie = petStore.findAllAnimalBySpecie(ANIMAL_TYPE);
        assertEquals(animal, bySpecie.get(0));
    }

    @Test
    public void testSetAndGetAgeCorrectly(){
        animal.setAge(42);
        assertEquals(42, animal.getAge());
    }
}

