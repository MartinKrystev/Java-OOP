package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut{
    private static final double DEFAULT_OXYGEN = 50.0;

    public Geodesist(String name) {
        super(name, DEFAULT_OXYGEN);
    }

    @Override
    public boolean canBreath() {
        return this.getOxygen() > 0;
    }
}
