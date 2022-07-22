package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GarageTests {
    private static final String CAR_BRAND_BMW = "bmw";
    private static final int CAR_MAX_SPEED_300 = 300;
    private static final double CAR_PRICE_2000 = 2_000;

    private static final String CAR_BRAND_AUDI = "audi";
    private static final int CAR_MAX_SPEED_280 = 280;
    private static final double CAR_PRICE_1000 = 1_000;

    private Garage garage;
    private Car bmw;
    private Car audi;
    private Car dummyCar;
    private List<Car> cars;

    @Before
    public void setup() {
        garage = new Garage();
        bmw = new Car(CAR_BRAND_BMW, CAR_MAX_SPEED_300, CAR_PRICE_2000);
        audi = new Car(CAR_BRAND_AUDI, CAR_MAX_SPEED_280, CAR_PRICE_1000);
        dummyCar = null;
        cars = new ArrayList<>();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testModifyUnmodifiableList() {
        garage.getCars().clear();
    }

    @Test
    public void testCarsWithSpeedAbove() {
        garage.addCar(audi);
        garage.addCar(bmw);
        List<Car> expected = new ArrayList<>();
        expected.add(bmw);

        List<Car> founded = garage.findAllCarsWithMaxSpeedAbove(290);

        Assert.assertEquals(expected, founded);
    }

    @Test
    public void testGetCount() {
        garage.addCar(bmw);
        int returnedCount = garage.getCount();
        Assert.assertEquals(1, returnedCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingCar() {
        garage.addCar(dummyCar);
    }

    @Test
    public void testGetTheMostExpensiveCar() {
        garage.addCar(audi);
        garage.addCar(bmw);

        Car result = garage.getTheMostExpensiveCar();

        Assert.assertEquals(bmw, result);
    }

    @Test
    public void testFindAllCarsByBrand() {
        garage.addCar(audi);
        garage.addCar(bmw);
        List<Car> expected = new ArrayList<>();
        expected.add(audi);

        List<Car> result = garage.findAllCarsByBrand("audi");

        Assert.assertEquals(expected, result);
    }

}