package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player tommy, Collection<Player> civilPlayers) {
        Repository<Gun> tommyGunRepository = tommy.getGunRepository();
        ArrayDeque<Gun> tommyGuns = new ArrayDeque<>(tommyGunRepository.getModels());
        ArrayDeque<Player> players = new ArrayDeque<>(civilPlayers);

        Player player = players.poll();
        Gun gun = tommyGuns.poll();

        //Tommy is shooting
        while (player != null && gun != null) {
            while (gun.canFire() && player.isAlive()) {
                int shot = gun.fire();
                player.takeLifePoints(shot);
            }

            if (gun.canFire()) {
                player = players.poll();
            } else {
                gun = tommyGuns.poll();
            }
        }

        //Civil players are shooting
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                Deque<Gun> civilPlayerGuns = new ArrayDeque<>(civilPlayer.getGunRepository().getModels());
                Gun civilPlayerGun = civilPlayerGuns.poll();
                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && tommy.isAlive()) {
                        int shot = civilPlayerGun.fire();
                        tommy.takeLifePoints(shot);
                    }

                    if (!tommy.isAlive()) {
                        return;
                    }
                   civilPlayerGun =  civilPlayerGuns.poll();
                }
            }
        }

    }
}
