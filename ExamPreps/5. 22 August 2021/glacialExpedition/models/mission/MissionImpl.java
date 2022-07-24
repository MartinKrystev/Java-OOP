package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Collection<String> allExhibits = state.getExhibits();

        for (Explorer e : explorers) {
            while (e.canSearch() && allExhibits.iterator().hasNext()) {

                e.search();
                String currExhibit = allExhibits.iterator().next();

                e.getSuitcase().getExhibits().add(currExhibit);

                allExhibits.remove(currExhibit);

            }

        }

    }
}
