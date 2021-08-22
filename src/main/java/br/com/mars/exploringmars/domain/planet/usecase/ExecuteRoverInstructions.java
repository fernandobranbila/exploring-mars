package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
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
        validateInstructions(roverInstructions);
        var plateau = findPlateauByIdProvider.execute(plateauId);
        for (char ch : roverInstructions.toCharArray()) {
            var rover = findRoverByIdProvider.execute(roverId);
            executeRoverInstructionsStrategyList.stream()
                    .filter(strategy -> strategy.filterInstruction(ch, rover.getFacingSide()))
                    .findFirst()
                    .ifPresent(strategy ->
                            strategy.execute(
                                    plateau,
                                    rover,
                                    ch
                            )
                    );
        }
        return rover;
    }

    private void validateInstructions(String roverInstructions) {
        if (!roverInstructions.replaceAll("[MLR]", "").isEmpty())
            throw new UnprocessableEntityException("Invalid rover instructions input");
    }
}
