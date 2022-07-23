package spaceStation.models.astronauts;

import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY;
import static spaceStation.common.ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO;

public abstract class BaseAstronaut implements Astronaut {
    private String name;
    private double oxygen;
    private Bag bag;

    public BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public void breath() {
        this.setOxygen(Math.max(0, this.getOxygen() - 10));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public String toString() {
        String allItems = this.getBag().getItems().size() == 0 ? "none" : String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, this.bag.getItems());

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(REPORT_ASTRONAUT_NAME, this.name)).append(System.lineSeparator());
        sb.append(String.format(REPORT_ASTRONAUT_OXYGEN, this.oxygen)).append(System.lineSeparator());
        sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, allItems));

        return sb.toString().trim();
    }
}
