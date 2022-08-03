package viceCity.core.interfaces;

import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.*;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private static final int TOMMY_START_HEALTH = 100;
    private static final int CIVIL_PLAYER_HEALTH = 50;

    private Player tommy;
    private Map<String, Player> civilPlayers;
    private ArrayDeque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        tommy = new MainPlayer();
        civilPlayers = new LinkedHashMap<>();
        guns = new ArrayDeque<>();
        neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        CivilPlayer civilPlayer = new CivilPlayer(name);
        civilPlayers.putIfAbsent(name, civilPlayer);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return GUN_TYPE_INVALID;
        }
        guns.offer(gun);

        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gunToAdd = guns.poll();

        if (gunToAdd == null) {
            return GUN_QUEUE_IS_EMPTY;
        }

        if (name.equals("Vercetti")) {
            tommy.getGunRepository().add(gunToAdd);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gunToAdd.getName(), tommy.getName());
        }

        Player civilPlayer = civilPlayers.get(name);
        if (civilPlayer == null) {
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        civilPlayer.getGunRepository().add(gunToAdd);
        return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gunToAdd.getName(), name);
    }

    @Override
    public String fight() {
        neighbourhood.action(tommy, civilPlayers.values());

        if (tommy.getLifePoints() == TOMMY_START_HEALTH &&
                civilPlayers.values().stream().allMatch(p -> p.getLifePoints() == 50)) {
            return FIGHT_HOT_HAPPENED;
        }

        List<Player> deadPlayers = civilPlayers.values()
                .stream()
                .filter(p -> !p.isAlive())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(FIGHT_HAPPENED).append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, tommy.getLifePoints())).append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayers.size())).append(System.lineSeparator());
        sb.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.size() - deadPlayers.size()));

        for (Player deadPlayer : deadPlayers) {
            civilPlayers.remove(deadPlayer.getName());
        }

        return sb.toString().trim();
    }
}
