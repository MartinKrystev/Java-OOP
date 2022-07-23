package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {
    private static final double DEFAULT_OXYGEN = 70.0;

    public Biologist(String name) {
        super(name, DEFAULT_OXYGEN);
    }

    @Override
    public void breath() {
        this.setOxygen(Math.max(0, this.getOxygen() - 5));
    }

    @Override
    public boolean canBreath() {
        return this.getOxygen() > 0;
    }
}
