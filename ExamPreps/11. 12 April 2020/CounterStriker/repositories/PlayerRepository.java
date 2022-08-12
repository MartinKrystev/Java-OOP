package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static CounterStriker.common.ExceptionMessages.INVALID_PLAYER_REPOSITORY;

public class PlayerRepository implements Repository<Player> {
    private List<Player> players;

    public PlayerRepository() {
        this.players = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return this.players;
    }

    @Override
    public void add(Player model) {
        if (model == null) {
            throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
        }
        players.add(model);
    }

    @Override
    public boolean remove(Player model) {
        Player toRemove = players.stream().filter(p -> p.equals(model)).findFirst().orElse(null);

        if (toRemove != null){
            players.remove(toRemove);
            return true;
        }
        return false;
    }

    @Override
    public Player findByName(String name) {
        Player toReturn = players.stream().filter(p -> p.getUsername().equals(name)).findFirst().orElse(null);
        return toReturn;
    }
}
