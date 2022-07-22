package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private static House house;
    private static Cat cat;

    private static final String HOUSE_NAME = "Palace";
    private static final String CAT_NAME = "Pesho";
    private static final String NONE_EXISTING_CAT = "Nafarforii";
    private static final String STATISTICS_MESSAGE = "The cat Pesho is in the house Palace!";

    private static final int HOUSE_CAPACITY = 2;
    private static final int HOUSE_COUNT = 1;
    private static final int ZERO_COUNT = 0;
    private static final int INVALID_HOUSE_CAPACITY = -1;

    @Before
    public void setUp() {
        house = new House(HOUSE_NAME, HOUSE_CAPACITY);
        cat = new Cat(CAT_NAME);
        house.addCat(cat);
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidHouseNameThrows() {
        new House(null, HOUSE_CAPACITY);
    }

    @Test
    public void testGetHouseNameCorrectly() {
        Assert.assertEquals(HOUSE_NAME, house.getName());
    }

    @Test
    public void testGetHouseCapacityCorrectly() {
        Assert.assertEquals(HOUSE_CAPACITY, house.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacityThrows() {
        new House(HOUSE_NAME, INVALID_HOUSE_CAPACITY);
    }

    @Test
    public void testGetCountCorrectly() {
        Assert.assertEquals(HOUSE_COUNT, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverCapacityThrows() {
        house.addCat(cat);
        house.addCat(cat);
    }

    @Test
    public void testRemoveCatCorrectly() {
        house.removeCat(cat.getName());
        Assert.assertEquals(ZERO_COUNT, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCatNameThrows() {
        house.removeCat(NONE_EXISTING_CAT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleThrows() {
        house.catForSale(NONE_EXISTING_CAT);
    }

    @Test
    public void testCatForSaleCorrectly() {
        Cat catForSale = house.catForSale(cat.getName());
        boolean isHungry = catForSale.isHungry();
        Assert.assertFalse(isHungry);
    }

    @Test
    public void testReturnStatisticsCorrectly() {
        Assert.assertEquals(STATISTICS_MESSAGE, house.statistics());
    }
}
