package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.gateway.inbound.MoveRoverInbound;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.infastructure.planet.gateway.FindPlateauByIdProvider;
import br.com.mars.exploringmars.infastructure.planet.gateway.FindRoverByIdProvider;

import javax.inject.Named;
import java.util.List;

@Named
public class ExecuteRoverInstructions implements MoveRoverInbound {

    private final List<ExecuteRoverInstructionsStrategy> executeRoverInstructionsStrategyList;

    private final FindRoverByIdProvider findRoverByIdProvider;

    private final FindPlateauByIdProvider findPlateauByIdProvider;

    public ExecuteRoverInstructions(List<ExecuteRoverInstructionsStrategy> executeRoverInstructionsStrategyList, FindRoverByIdProvider findRoverByIdProvider, FindPlateauByIdProvider findPlateauByIdProvider) {
        this.executeRoverInstructionsStrategyList = executeRoverInstructionsStrategyList;
        this.findRoverByIdProvider = findRoverByIdProvider;
        this.findPlateauByIdProvider = findPlateauByIdProvider;
    }

    @Override
    public Rover execute(Long planetId, Long plateauId, Long roverId, String roverInstructions) {
        validateInstructions(); //regex LRM
        var plateau = findPlateauByIdProvider.execute(plateauId);
        var rover = findRoverByIdProvider.execute(roverId);
        for (char ch : roverInstructions.toCharArray()) {
            executeRoverInstructionsStrategyList.stream()
                    .filter(strategy -> strategy.filterInstruction(ch, rover.getFacingSide()))
                    .findFirst()
                    .ifPresent(strategy ->
                            strategy.execute(
                                    plateau,
                                    findRoverByIdProvider.execute(roverId),
                                    ch
                            )
                    );
        }

        return rover;

    }
}
