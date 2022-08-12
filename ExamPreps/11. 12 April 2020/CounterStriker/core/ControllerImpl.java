package CounterStriker.core;

import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static CounterStriker.common.ExceptionMessages.*;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_GUN;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_PLAYER;

public class ControllerImpl implements Controller {
    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name, bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(INVALID_GUN_TYPE); //TODO Invalid gun type! -> might be a problem with the "!"
        }

        guns.add(gun);
        return String.format(SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gun = this.guns.findByName(gunName);
        if (gun == null) {
            throw new NullPointerException(GUN_CANNOT_BE_FOUND);
        }

        Player player;
        switch (type) {
            case "Terrorist":
                player = new Terrorist(username, health, armor, gun);
                break;
            case "CounterTerrorist":
                player = new CounterTerrorist(username, health, armor, gun);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        players.add(player);
        return String.format(SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        List<Player> playersAlive = players.getModels().stream().filter(Player::isAlive).collect(Collectors.toList());

        return this.field.start(playersAlive);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

       this.players
                .getModels()
                .stream()
                .sorted((a, b) -> {

                    int result = a.getClass().getSimpleName().compareTo(b.getClass().getSimpleName());

                    if (result == 0) {
                        result = Integer.compare(b.getHealth(), a.getHealth());
                    }

                    if (result == 0) {
                        result = a.getUsername().compareTo(b.getUsername());
                    }

                    return result;
                })
               .forEach(p -> sb.append(p).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
