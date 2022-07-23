package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{
    private int deadAstronauts;

    @Override
    public int getDeadAstronauts() {
        return this.deadAstronauts;
    }

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        List<Astronaut> astronautList = new ArrayList<>(astronauts);
        List<String> planetList = new ArrayList<>(planet.getItems());

        for (Astronaut a : astronautList) {

            for (int i = 0; i < planetList.size(); i++) {
                String item = planetList.get(i);

                a.breath();
                a.getBag().getItems().add(item);
                planetList.remove(item);

                i--;

                if (!a.canBreath()) {
                    this.deadAstronauts++;
                    break;
                }

            }
        }
    }
}
