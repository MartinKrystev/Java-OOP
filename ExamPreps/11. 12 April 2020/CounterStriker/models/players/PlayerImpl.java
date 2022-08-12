package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

import static CounterStriker.common.ExceptionMessages.*;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
        this.isAlive = true;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    public void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void takeDamage(int points) {
        if (armor - points >= 0) {
            setArmor(getArmor() - points);
        } else {
            setArmor(0);
        }

        if (armor == 0){
            setHealth(getHealth() - points);
        }

        if (this.health <= 0) {
            setHealth(0);
            this.isAlive = false; // maybe not needed
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(": ").append(this.username);
        sb.append(System.lineSeparator());
        sb.append("--Health: ").append(this.health);
        sb.append(System.lineSeparator());
        sb.append("--Armor: ").append(this.armor);
        sb.append(System.lineSeparator());
        sb.append("--Gun: ").append(this.gun.getName());

        return sb.toString().trim();
    }
}
