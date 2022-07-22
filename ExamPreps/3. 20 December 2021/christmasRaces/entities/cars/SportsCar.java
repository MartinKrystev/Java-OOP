package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar {
    private static final int CUBIC_CENTIMETERS = 3_000;
    private static final int DEFAULT_HORSEPOWER_MIN = 250;
    private static final int DEFAULT_HORSEPOWER_MAX = 450;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < DEFAULT_HORSEPOWER_MIN || horsePower > DEFAULT_HORSEPOWER_MAX) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
    }

}
