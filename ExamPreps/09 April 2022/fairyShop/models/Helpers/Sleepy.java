package fairyShop.models.Helpers;

public class Sleepy extends BaseHelper{
    private static final int SLEEPY_ENERGY = 50;

    public Sleepy(String name) {
        super(name, SLEEPY_ENERGY);
    }

    @Override
    public void work() {
        this.setEnergy(Math.max(0, SLEEPY_ENERGY - 15));
    }

}
