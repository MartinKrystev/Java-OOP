package CounterStriker.models.field;

import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field{

    @Override
    public String start(Collection<Player> players) {
        List<Player> terrorists = players.stream().filter(p -> p.getClass().getSimpleName().equals("Terrorist")).collect(Collectors.toList());
        List<Player> counterTerrorists = players.stream().filter(p -> p.getClass().getSimpleName().equals("CounterTerrorist")).collect(Collectors.toList());

        while (!terrorists.isEmpty() && !counterTerrorists.isEmpty()){
            //terrorists are shooting
//            for (Player terrorist : terrorists) {
//                for (int ct = 0; ct < counterTerrorists.size(); ct++) {
//                    Player counterTerr = counterTerrorists.get(ct);
//                    int damage = terrorist.getGun().fire();
//                    counterTerr.takeDamage(damage);
//                    if (!counterTerr.isAlive()){
//                        counterTerrorists.remove(ct);
//                        ct--;
//                    }
//                }
//            }
            for (Player terrorist : terrorists) {
                for (Player counterTerrorist : counterTerrorists) {
                    int damage = terrorist.getGun().fire();
                    counterTerrorist.takeDamage(damage);
                    if(!counterTerrorist.isAlive()) {
                        counterTerrorists.remove(counterTerrorist);
                        break;
                    }
                }

            }

            //counterTerrorists are shooting
//            for (Player counterTerrorist : counterTerrorists) {
//                for (int t = 0; t < terrorists.size(); t++) {
//                    Player terrorist = terrorists.get(t);
//                    int damage = counterTerrorist.getGun().fire();
//                    terrorist.takeDamage(damage);
//                    if (!terrorist.isAlive()){
//                        terrorists.remove(t);
//                        t--;
//                    }
//                }
//            }
            for (Player counterTerrorist : counterTerrorists) {
                for (Player terrorist : terrorists) {
                    int damage = counterTerrorist.getGun().fire();
                    terrorist.takeDamage(damage);
                    if (!terrorist.isAlive()){
                        terrorists.remove(terrorist);
                        break;
                    }
                }
            }

        }

        String winner = terrorists.size() > 0 ? "Terrorist wins!" : "Counter Terrorist wins!";

        return winner;
    }
}
