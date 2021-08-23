package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.UpdateRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.domain.planet.model.Rover;

import javax.inject.Named;

@Named
public class MoveRoverEast implements ExecuteRoverInstructionsStrategy {

    private final UpdateRoverOutbound updateRoverOutbound;

    private final CheckIfWillCrashOnOtherRover checkIfWillCrashOnOtherRover;

    public MoveRoverEast(UpdateRoverOutbound updateRoverOutbound, CheckIfWillCrashOnOtherRover checkIfWillCrashOnOtherRover) {
        this.updateRoverOutbound = updateRoverOutbound;
        this.checkIfWillCrashOnOtherRover = checkIfWillCrashOnOtherRover;
    }

    @Override
    public Boolean filterInstruction(char instruction, FacingSide roverFacingSide) {
        return instruction == 'M' && roverFacingSide == FacingSide.E;
    }

    @Override
    public Rover execute(Plateau plateau, Rover rover) {
        var roverExpectedXPosition = rover.getXPosition() + 1;
        var roverExpectedYPosition = rover.getYPosition();
        rover.checkIfValidMoveOnPlateau(plateau, roverExpectedXPosition, roverExpectedYPosition);
        checkIfWillCrashOnOtherRover.execute(plateau, roverExpectedXPosition, roverExpectedYPosition);
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
