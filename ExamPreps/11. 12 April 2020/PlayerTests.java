package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {

    private Gun pistol;
    private Gun rifle;
    private Player player;

    @Before
    public void setup(){
        pistol = new Gun("Pistol", 11);
        rifle = new Gun("Rifle", 30);
        player = new Player("Pesho", 100);
    }

    @Test(expected = NullPointerException.class)
    public void testSetUserNameThrows() {
        Player playerNull = new Player(null, 999);
    }

    @Test
    public void testSetUserNameWorks() {
        Assert.assertEquals("Pesho", player.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHealthThrows() {
        Player player2 = new Player("Nikodim", -2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableCollectionThrows() {
        player.getGuns().clear();
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageThrows() {
        Player player3 = new Player("AttackedOneIsAlreadyDead", 0);
        player3.takeDamage(3);
    }

    @Test
    public void testTakeDamageWorks() {
        Player player3 = new Player("Attacked_Lives", 5);

        player3.takeDamage(2);

        Assert.assertEquals(3, player3.getHealth());
    }
    @Test
    public void testTakeDamageAndPlayerDies() {
        Player player4 = new Player("Attacked_Dies", 5);

        player4.takeDamage(6);

        Assert.assertEquals(0, player4.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testAddGunThrows() {
        Gun gunNull = null;
        player.addGun(gunNull);
    }

    @Test
    public void testAddGunWorks() {
        player.addGun(pistol);
        player.addGun(rifle);

        Assert.assertEquals(2, player.getGuns().size());
    }

    @Test
    public void testRemoveGunWorks() {
        player.addGun(pistol);
        player.addGun(rifle);

        player.removeGun(pistol);

        Assert.assertEquals(1, player.getGuns().size());
    }

    @Test
    public void testGetGunWorks() {
        player.addGun(pistol);
        player.addGun(rifle);

        Assert.assertEquals(pistol, player.getGun("Pistol"));
    }

    @Test
    public void testGetBullets() {
       Assert.assertEquals(11, pistol.getBullets());
    }
}
