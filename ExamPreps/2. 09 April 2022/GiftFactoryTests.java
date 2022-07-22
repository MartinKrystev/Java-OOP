package gifts;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class GiftFactoryTests {

    @Test
    public void testCreatingGiftAndAddItToTheCollection() {
        Gift gift = new Gift("Toy", 5.5);
        Collection<Gift> data = new ArrayList<>();
        data.add(gift);

        Assert.assertEquals(1, data.size());
    }

    @Test
    public void testConstructor() {
        GiftFactory giftFactory = new GiftFactory();
        Assert.assertEquals(0, giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGiftWithSameNames() {
        Gift gift1 = new Gift("Toy", 5.5);
        Gift gift2 = new Gift("Toy", 6.50);

        GiftFactory data = new GiftFactory();
        data.createGift(gift1);
        data.createGift(gift2);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGift() {
        GiftFactory data = new GiftFactory();
        data.removeGift(null);
    }

    @Test
    public void testRemovingGiftIsRemovedSuccessfully() {
        GiftFactory data = new GiftFactory();
        Gift gift1 = new Gift("Toy", 5.5);
        data.createGift(gift1);

        data.removeGift("Toy");

        Assert.assertFalse(data.removeGift("Toy"));
    }

    @Test
    public void testFindPresentWithTheLeastMagic() {
        Gift gift1 = new Gift("Toy", 5.5);
        Gift gift2 = new Gift("Toy2", 6.50);
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);

        Gift searchedOne = giftFactory.getPresentWithLeastMagic();

        Assert.assertEquals(gift1, searchedOne);
    }

    @Test
    public void testGetPresentByName() {
        Gift gift1 = new Gift("Toy", 5.5);
        Gift gift2 = new Gift("Toy2", 6.50);
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);

        Gift searchedOne = giftFactory.getPresent("Toy");

        Assert.assertEquals(gift1, searchedOne);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testModifyingUnmodifiableCollection() {
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.getPresents().clear();
    }

}
