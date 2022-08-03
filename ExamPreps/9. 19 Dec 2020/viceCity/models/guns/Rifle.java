package viceCity.models.guns;

public class Rifle extends BaseGun {
    private static final int DEFAULT_BULLETS_PER_BARREL = 50;
    private static final int DEFAULT_TOTAL_BULLETS = 500;

    public Rifle(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            reload();
        }

        setBulletsPerBarrel(getBulletsPerBarrel() - 5);

        return 5;
    }

    private void reload() {
        setTotalBullets(getTotalBullets() - DEFAULT_BULLETS_PER_BARREL);
        setBulletsPerBarrel(DEFAULT_BULLETS_PER_BARREL);
    }
}
