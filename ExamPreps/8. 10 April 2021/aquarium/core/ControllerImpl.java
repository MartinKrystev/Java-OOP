package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DecorationRepository decorationRepository;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorationRepository = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium = null;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
        }

        if (aquarium == null) {
            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }

        this.aquariums.add(aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration = null;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
        }

        if (decoration == null) {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }

        this.decorationRepository.add(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = this.decorationRepository.findByType(decorationType);

        if (decoration == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        Aquarium aquarium = getAquariumByName(aquariumName);
        aquarium.addDecoration(decoration);

        this.decorationRepository.remove(decoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish = null;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
        }

        if (fish == null) {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        Aquarium aquarium = getAquariumByName(aquariumName);

        if (!aquarium.getClass().getSimpleName().substring(0, 3).equals(fishType.substring(0, 3))) {
            return WATER_NOT_SUITABLE;
        }

        try {
            aquarium.addFish(fish);
        } catch (IllegalArgumentException e) {
            return NOT_ENOUGH_CAPACITY;
        }

        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = getAquariumByName(aquariumName);

        aquarium.feed();

        return String.format(FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = getAquariumByName(aquariumName);

        double fishValue = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        double decorationValue = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();

        return String.format(VALUE_AQUARIUM, aquariumName, fishValue + decorationValue);
    }

    @Override
    public String report() {

//        StringBuilder sb = new StringBuilder();
//        for (Aquarium a : this.aquariums) {
//            sb.append(a.getInfo()).append(System.lineSeparator());
//        }
//
//        return sb.toString().trim();

        return this.aquariums.stream()
                .map(Aquarium::getInfo)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }

    private Aquarium getAquariumByName(String aquariumName) {
        return this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
    }
}
