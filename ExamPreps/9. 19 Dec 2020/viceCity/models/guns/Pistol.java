package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int DEFAULT_BULLETS_PER_BARREL = 10;
    private static final int DEFAULT_TOTAL_BULLETS = 100;

    public Pistol(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            reload();
        }

        setBulletsPerBarrel(getBulletsPerBarrel() - 1);

        return 1;
    }

    private void reload() {
        setTotalBullets(getTotalBullets() - DEFAULT_BULLETS_PER_BARREL);
        setBulletsPerBarrel(DEFAULT_BULLETS_PER_BARREL);
        setBulletsPerBarrel(DEFAULT_BULLETS_PER_BARREL);
    }
}
