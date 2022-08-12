package CounterStriker.models.guns;

public class Pistol extends GunImpl{
    private static final int BULLETS_PER_SHOT = 1;

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() >= BULLETS_PER_SHOT) {
            setBulletsCount(getBulletsCount() - BULLETS_PER_SHOT);
            return BULLETS_PER_SHOT;
        }
        return 0;
    }
}
