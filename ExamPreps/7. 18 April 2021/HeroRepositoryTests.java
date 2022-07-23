package heroRepository;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class HeroRepositoryTests {

    @Test
    public void testGetCount(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero1 = new Hero("Pesho", 1);
        heroRepository.create(hero1);

        Assert.assertEquals(1, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullHeroThrows() {
        HeroRepository heroRepository = new HeroRepository();

        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateExistingHeroThrows() {
        HeroRepository heroRepository = new HeroRepository();
        Hero hero1 = new Hero("Pesho", 1);
        Hero hero2 = new Hero("Pesho", 2);

        heroRepository.create(hero1);
        heroRepository.create(hero2);
    }

    @Test
    public void testCreateHeroCorrectly() {
        HeroRepository heroRepository = new HeroRepository();
        Hero hero1 = new Hero("Pesho", 1);

        String expected = "Successfully added hero Pesho with level 1";

        Assert.assertEquals(expected, heroRepository.create(hero1));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveThrows(){
        HeroRepository heroRepository = new HeroRepository();

        heroRepository.remove(null);
    }

    @Test
    public void testRemoveCorrectly() {
        HeroRepository heroRepository = new HeroRepository();
        Hero hero1 = new Hero("Pesho", 1);
        heroRepository.create(hero1);

        Assert.assertTrue(heroRepository.remove("Pesho"));
    }

    @Test
    public void testGetHeroWithHighestLevel(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero1 = new Hero("Pesho", 1);
        heroRepository.create(hero1);
        Hero hero2 = new Hero("Gosho", 2);
        heroRepository.create(hero2);

        Assert.assertEquals(hero2, heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testGetHero(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero1 = new Hero("Pesho", 1);
        heroRepository.create(hero1);
        Hero hero2 = new Hero("Gosho", 2);
        heroRepository.create(hero2);

        Assert.assertEquals(hero2, heroRepository.getHero("Gosho"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetHeroesThrows() {
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.getHeroes().clear();
    }

}
