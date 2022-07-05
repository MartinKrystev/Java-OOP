package com.company.Inheritance.Lab.P04RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList {

    public Object getRandomElement() {
        int index = new Random().nextInt(super.size());
        return super.remove(index);
    }

}
