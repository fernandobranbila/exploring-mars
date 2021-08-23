package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.UpdateRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.domain.planet.model.Rover;

import javax.inject.Named;

@Named
public class TurnRoverFacingSideLeft implements ExecuteRoverInstructionsStrategy {

    private final UpdateRoverOutbound updateRoverOutbound;

    public TurnRoverFacingSideLeft(UpdateRoverOutbound updateRoverOutbound) {
        this.updateRoverOutbound = updateRoverOutbound;
    }

    @Override
    public Boolean filterInstruction(char instruction, FacingSide roverFacingSide) {
        return instruction == 'L';
    }

    @Override
    public Rover execute(Plateau plateau, Rover rover) {
        return updateRoverOutbound.execute(
                new Rover(
                        rover.getId(),
                        rover.getPlateauId(),
                        rover.getName(),
                        rover.getXPosition(),
                        rover.getYPosition(),
                        rover.getFacingSideMovingToLeft()
                )
        );
    }
}
