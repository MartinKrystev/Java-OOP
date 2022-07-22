package fairyShop.models.Helpers;

import fairyShop.common.ExceptionMessages;
import fairyShop.models.Instruments.Instrument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseHelper implements Helper {
    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    public BaseHelper(String name, int energy) {
        this.setName(name);
        this.energy = energy;
        this.instruments = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void work() {
        this.setEnergy(this.getEnergy() - 10);
        if (this.getEnergy() < 0) {
            this.setEnergy(0);
        }
    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        if (this.getEnergy() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        List<Instrument> notBroken = this.instruments
                .stream()
                .filter(i -> !i.isBroken())
                .collect(Collectors.toList());

        sb.append(String.format("Name: %s", this.name)).append(System.lineSeparator());
        sb.append(String.format("Energy: %d", this.energy)).append(System.lineSeparator());
        sb.append(String.format("Instruments: %d not broken left", notBroken.size())).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
