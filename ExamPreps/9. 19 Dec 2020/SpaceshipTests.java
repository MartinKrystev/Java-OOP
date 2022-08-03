package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private final static String BATTLE_CRUISER = "BattleCruiser";
    private final static int BATTLE_CRUISER_CAPACITY = 5;
    private final static String ASTRONAUT_JOHN_NAME = "John";
    private final static double ASTRONAUT_JOHN_OXYGEN = 25.5;
    private final static String ASTRONAUT_MIKE_NAME = "Mike";
    private final static double ASTRONAUT_MIKE_OXYGEN = 30.5;

    private Spaceship spaceship;
    private Astronaut john;
    private Astronaut mike;

    @Before
    public void setup() {
        spaceship = new Spaceship(BATTLE_CRUISER, BATTLE_CRUISER_CAPACITY);
        
        john = new Astronaut(ASTRONAUT_JOHN_NAME, ASTRONAUT_JOHN_OXYGEN);
        mike = new Astronaut(ASTRONAUT_MIKE_NAME, ASTRONAUT_MIKE_OXYGEN);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameThrows() {
        Spaceship spaceshipNull = new Spaceship(null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityThrows() {
        Spaceship motherBoard = new Spaceship("MamaBoard", -1);
    }

    @Test
    public void testSetAndCapacityNameCorrectly() {
        Spaceship carrier = new Spaceship("Carrier", 2);

        Assert.assertEquals("Carrier", carrier.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToFullCapacityThrows() {
        Spaceship spaceship = new Spaceship("BattleCruiser", 1);

        spaceship.add(john);
        spaceship.add(mike);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSameAstronautThrows() {
        spaceship.add(john);
        spaceship.add(john);
    }

    @Test
    public void testAddAstronautCorrectly() {
        spaceship.add(john);
        Assert.assertEquals("BattleCruiser", spaceship.getName());
    }

    @Test
    public void testRemoveCorrectly() {
        spaceship.add(john);
        spaceship.add(mike);

        Assert.assertTrue(spaceship.remove("John"));
    }

    @Test
    public void testGetCount() {
        spaceship.add(john);

        Assert.assertEquals(1, spaceship.getCount());
    }

    @Test
    public void testGetOxygen() {
        Assert.assertEquals(25.5, john.getOxygenInPercentage(), 0.01);
    }
}
