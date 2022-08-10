package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComputerManagerTests {

    private Computer computer;
    private ComputerManager computerManager;

    @Before
    public void setup() {
        computer = new Computer("Dell", "R2D2", 999.99);
        computerManager = new ComputerManager();
        computerManager.addComputer(computer);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputersUnmodifiableListThrows() {
        computerManager.getComputers().clear();
    }

    @Test
    public void testGetCount() {
        Assert.assertEquals(1, computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerThrows() {
        computerManager.addComputer(computer);
    }

    @Test
    public void testRemoveComputerWorks() {
        Computer result = computerManager.removeComputer("Dell", "R2D2");
        Assert.assertEquals(computer, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerThrows() {
        computerManager.getComputer("null", "null");
    }

    @Test
    public void testGetComputerWorks() {
        Computer result = computerManager.getComputer("Dell", "R2D2");
        Assert.assertEquals(computer, result);
    }

    @Test
    public void testGetComputerByManufacturerWorks() {
        List<Computer> result = computerManager.getComputersByManufacturer("Dell");
        Assert.assertEquals(computer, result.get(0));
    }

    @Test
    public void testGetPriceWorks() {
        Assert.assertEquals(999.99, computer.getPrice(), 0.1);
    }

}