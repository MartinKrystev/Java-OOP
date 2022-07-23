package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{
    private static final double DEFAULT_OXYGEN = 90.0;

    public Meteorologist(String name) {
        super(name, DEFAULT_OXYGEN);
    }

    @Override
    public boolean canBreath() {
        return this.getOxygen() > 0;
    }
}
