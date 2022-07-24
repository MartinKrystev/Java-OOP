package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer = null;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }

        this.explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);

        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }

        this.stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = this.explorerRepository.byName(explorerName);

        if (explorer == null) {
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }

        this.explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        Collection<Explorer> suitableExplorers = this.explorerRepository.getCollection()
                .stream()
                .filter(e -> e.getEnergy() > 50)
                .collect(Collectors.toList());

        if (suitableExplorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);

        Mission mission = new MissionImpl();
        mission.explore(state, suitableExplorers);

        int retiredExplorers = (int) suitableExplorers.stream()
                .filter(e -> e.getEnergy() == 0)
                .count();

        this.exploredStates++;

        return String.format(STATE_EXPLORER, stateName, retiredExplorers);
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_STATE_EXPLORED, this.exploredStates)).append(System.lineSeparator());
        sb.append(FINAL_EXPLORER_INFO).append(System.lineSeparator());

        for (Explorer explorer : this.explorerRepository.getCollection()) {
            sb.append(String.format(FINAL_EXPLORER_NAME, explorer.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append(System.lineSeparator());

            String suitable = explorer.getSuitcase().getExhibits().size() == 0
                    ? "None"
                    : String.join(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, explorer.getSuitcase().getExhibits());

            sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, suitable)).append(System.lineSeparator());
           // sb.append(suitable).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
