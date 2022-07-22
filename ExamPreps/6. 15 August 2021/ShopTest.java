package shopAndGoods;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {

    //TODO boolean

    @Test
    public void testConstructor(){
        Shop shop = new Shop();
        Assert.assertEquals(null, shop.getShelves().get("Shelves1"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetShelvesTrows(){
        Shop shop = new Shop();
        shop.getShelves().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsThrowsTheShelfDoesNotExist() throws OperationNotSupportedException {
        Shop shop = new Shop();
        Goods goods = new Goods("test1", "test2");
        shop.addGoods("Doesn't exist", goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsThrowsTheShelfIsTaken() throws OperationNotSupportedException {
        Shop shop = new Shop();
        Goods goods = new Goods("test1", "testCode1");
        Goods goods2 = new Goods("test2", "testCode2");

        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves1", goods2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodsThrowsItemAlreadyExist() throws OperationNotSupportedException {
        Shop shop = new Shop();
        Goods goods = new Goods("test1", "testCode1");
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves2", goods);
    }

    @Test
    public void testAddGoodsCorrectly() throws OperationNotSupportedException {
        Shop shop = new Shop();
        Goods goods = new Goods("test1", "testCode1");
        String expected = "Goods: testCode1 is placed successfully!";

        String result = shop.addGoods("Shelves1", goods);

        Assert.assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsThrowsTheShelfDoesNotExist(){
        Shop shop = new Shop();
        Goods goods = new Goods("test1", "testCode1");

        shop.removeGoods("NonExisting", goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsThrowsGoodDoesNotExist(){
        Shop shop = new Shop();
        Goods empty = new Goods("noName","noCode");

        shop.removeGoods("Shelves1", empty);
    }

    @Test
    public void testRemoveGoodsCorrectly() throws OperationNotSupportedException {
        Shop shop = new Shop();
        Goods goods = new Goods("test1", "testCode1");
        String expected = "Goods: testCode1 is removed successfully!";

        shop.addGoods("Shelves1", goods);
        String result = shop.removeGoods("Shelves1", goods);

        Assert.assertEquals(expected, result);
        Assert.assertNull(shop.getShelves().get("Shelves1"));
    }

    @Test
    public void testGoodsGetNameAndGoodsCode(){
        Goods goods = new Goods("test1", "testCode1");

        Assert.assertEquals("test1", goods.getName());
        Assert.assertEquals("testCode1", goods.getGoodsCode());
    }
}