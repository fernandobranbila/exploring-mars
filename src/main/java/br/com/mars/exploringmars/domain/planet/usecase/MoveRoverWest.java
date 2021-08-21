package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.UpdateRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.domain.planet.model.Rover;

import javax.inject.Named;

@Named
public class MoveRoverWest implements ExecuteRoverInstructionsStrategy {

    private final UpdateRoverOutbound updateRoverOutbound;

    public MoveRoverWest(UpdateRoverOutbound updateRoverOutbound) {
        this.updateRoverOutbound = updateRoverOutbound;
    }

    @Override
    public Boolean filterInstruction(char instruction, FacingSide roverFacingSide) {
        return instruction == 'M' && roverFacingSide == FacingSide.W;
    }

    @Override
    public Rover execute(Plateau plateau, Rover rover, char instruction) {
        var roverExpectedXPosition = rover.getXPosition() - 1;
        var roverExpectedYPosition = rover.getYPosition();
        rover.checkIfValidMoveOnPlateau(plateau, roverExpectedXPosition, roverExpectedYPosition);
        return updateRoverOutbound.execute(
                new Rover(
                        rover.getId(),
                        rover.getPlateauId(),
                        rover.getName(),
                        roverExpectedXPosition,
                        roverExpectedYPosition,
                        rover.getFacingSide()
                )
        );
    }
}
