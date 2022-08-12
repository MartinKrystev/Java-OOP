package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static CounterStriker.common.ExceptionMessages.INVALID_GUN_REPOSITORY;

public class GunRepository implements Repository<Gun>{
    private List<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.models;
    }

    @Override
    public void add(Gun model) {
        if (model == null){
            throw new NullPointerException(INVALID_GUN_REPOSITORY);
        }
        models.add(model);
    }

    @Override
    public boolean remove(Gun model) {
        Gun toRemove = models.stream().filter(m -> m.equals(model)).findFirst().orElse(null);

        if (toRemove != null) {
            models.remove(toRemove);
            return true;
        }
        return false;
    }

    @Override
    public Gun findByName(String name) {
        Gun toReturn = models.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
        return toReturn;
    }
}
