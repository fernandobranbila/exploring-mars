package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindRoverByPlateauIdAndXPositionAndYPositionOutbound;
import br.com.mars.exploringmars.domain.planet.model.Plateau;

import javax.inject.Named;

@Named
public class CheckIfWillCrashOnOtherRover {

    private final FindRoverByPlateauIdAndXPositionAndYPositionOutbound findRoverByPlateauIdAndXPositionAndYPositionOutbound;

    public CheckIfWillCrashOnOtherRover(FindRoverByPlateauIdAndXPositionAndYPositionOutbound findRoverByPlateauIdAndXPositionAndYPositionOutbound) {
        this.findRoverByPlateauIdAndXPositionAndYPositionOutbound = findRoverByPlateauIdAndXPositionAndYPositionOutbound;
    }

    public void execute(Plateau plateau, Integer roverExpectedXPosition, Integer roverExpectedYPosition) {
        if (findRoverByPlateauIdAndXPositionAndYPositionOutbound.execute(
                plateau.getId(),
                roverExpectedXPosition,
                roverExpectedYPosition
        ) != null)
            throw new UnprocessableEntityException("Impossible move on " + roverExpectedXPosition + ", " + roverExpectedYPosition + " space already occupied by another rover");
    }
}
