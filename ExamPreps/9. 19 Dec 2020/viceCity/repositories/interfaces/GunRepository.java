package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.*;

public class GunRepository implements Repository<Gun> {
    private Map<String, Gun> guns;

    public GunRepository() {
        this.guns = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        // return guns.values();
        return Collections.unmodifiableCollection(guns.values());
    }

    @Override
    public void add(Gun gun) {
        guns.putIfAbsent(gun.getName(), gun);
    }

    @Override
    public boolean remove(Gun gun) {
        return guns.remove(gun.getName()) != null;
    }

    @Override
    public Gun find(String name) {
        return guns.get(name);
    }
}
