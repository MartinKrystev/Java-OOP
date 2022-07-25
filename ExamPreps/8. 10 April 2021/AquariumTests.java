package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests {
    @Test
    public void testGetNameCorrectly() {
        Aquarium aquarium = new Aquarium("FishTank", 2);
        Assert.assertEquals("FishTank", aquarium.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameToNullThrows() {
        Aquarium aquarium = new Aquarium(null, 2);
    }

    @Test
    public void testSetNameCorrectly() {
        Aquarium aquarium = new Aquarium("FishTank", 2);
        Assert.assertEquals("FishTank", aquarium.getName());
    }

    @Test
    public void testGetCapacityCorrectly() {
        Aquarium aquarium = new Aquarium("FishTank", 2);
        Assert.assertEquals(2, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityToNegativeThrows() {
        Aquarium aquarium = new Aquarium("FishTank", -2);
    }

    @Test
    public void testSetCapacityCorrectly() {
        Aquarium aquarium = new Aquarium("FishTank", 3);
        Assert.assertEquals(3, aquarium.getCapacity());
    }

    @Test
    public void testGetCountCorrectly() {
        Aquarium aquarium = new Aquarium("FishTank", 2);
        Fish nemo = new Fish("Nemo");
        aquarium.add(nemo);
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFishThrows() {
        Aquarium aquarium = new Aquarium("FishTank", 1);
        Fish nemo = new Fish("Nemo");
        Fish betty = new Fish("Betty");
        aquarium.add(nemo);
        aquarium.add(betty);
    }

    @Test
    public void TestAddFishCorrectly(){
        Aquarium aquarium = new Aquarium("FishTank", 2);
        Fish nemo = new Fish("Nemo");
        aquarium.add(nemo);
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrows() {
        Aquarium aquarium = new Aquarium("FishTank", 1);
        Fish nemo = new Fish("Nemo");
        aquarium.remove("Betty");
    }

    @Test
    public void testRemoveFishCorrectly() {
        Aquarium aquarium = new Aquarium("FishTank", 1);
        Fish nemo = new Fish("Nemo");
        aquarium.add(nemo);

        aquarium.remove("Nemo");
        Assert.assertEquals(0, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishThrows() {
        Aquarium aquarium = new Aquarium("FishTank", 1);
        Fish nemo = new Fish("Nemo");
        aquarium.add(nemo);

        aquarium.sellFish("Betty");
    }

    @Test
    public void testSellFishCorrectly() {
        Aquarium aquarium = new Aquarium("FishTank", 1);
        Fish nemo = new Fish("Nemo");
        aquarium.add(nemo);

        Fish result = aquarium.sellFish("Nemo");

        Assert.assertEquals(nemo, result);
        Assert.assertFalse(result.isAvailable());
    }

    @Test
    public void testReportCorrectly(){
        Aquarium aquarium = new Aquarium("FishTank", 1);
        Fish nemo = new Fish("Nemo");
        aquarium.add(nemo);

        String expected = "Fish available at FishTank: Nemo";
        String result = aquarium.report();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testFishIsAvailable(){
        Fish nemo = new Fish("Nemo");
        boolean result = nemo.isAvailable();

        Assert.assertTrue(result);
    }

    @Test
    public void testSetAvailableCorrectly(){
        Fish nemo = new Fish("Nemo");
        nemo.setAvailable(false);

        Assert.assertFalse(nemo.isAvailable());
    }
}

