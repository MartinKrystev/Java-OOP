package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private static final String TYPE_CAT = "cat";
    private static final double ENERGY_CAT_20 = 20;

    private static final String TYPE_DOG = "dog";
    private static final double ENERGY_DOG_30 = 30;

    private static final String FARM_NAME_VALID = "ikea";
    private static final int FARM_CAPACITY_VALID = 50;
    private static final int FARM_CAPACITY_ONE = 1;

    private Farm farm;
    private Animal cat;
    private Animal dog;

    @Before
    public void setup() {
        farm = new Farm(FARM_NAME_VALID, FARM_CAPACITY_VALID);
        cat = new Animal(TYPE_CAT, ENERGY_CAT_20);
        dog = new Animal(TYPE_DOG, ENERGY_DOG_30);
    }

    @Test
    public void testFarmConstructor() {
        Farm result = new Farm("ikea", 50);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetCountAndGetName(){
        farm.add(cat);

        Assert.assertEquals(1, farm.getCount());
        Assert.assertEquals("ikea", farm.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalToFullCapacity() {
        Farm farm1 = new Farm("Farm", FARM_CAPACITY_ONE);
        farm1.add(cat);
        farm1.add(dog);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSameAnimalToFullCapacity() {
        farm.add(cat);
        farm.add(cat);
    }

    @Test
    public void testRemoveAnimal() {
        farm.add(cat);
        farm.add(dog);

        Assert.assertTrue(farm.remove("cat"));
        Assert.assertFalse(farm.remove("Pesho"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityInvalid() {
        Farm farm2 = new Farm("Fantastico", -5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNull(){
        Farm farm3 = new Farm(null, 3);
    }

    @Test
    public void testGetEnergy(){
        farm.add(cat);
        Assert.assertEquals(20.0, cat.getEnergy(),0.01);
    }

}
