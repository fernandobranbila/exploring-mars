package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.gateway.inbound.MoveRoverInbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlateauByIdOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindRoverByIdOutBound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.MoveRoverOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.TurnRoverSideOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.UpdateRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.Rover;

import javax.inject.Named;

@Named
public class Test implements MoveRoverInbound {

    private final FindRoverByIdOutBound findRoverByIdOutBound;

    private final UpdateRoverOutbound updateRoverOutbound;

    private final FindPlateauByIdOutbound findPlateauByIdOutbound;

    public Test(FindRoverByIdOutBound findRoverByIdOutBound, UpdateRoverOutbound updateRoverOutbound, FindPlateauByIdOutbound findPlateauByIdOutbound) {
        this.findRoverByIdOutBound = findRoverByIdOutBound;
        this.updateRoverOutbound = updateRoverOutbound;
        this.findPlateauByIdOutbound = findPlateauByIdOutbound;
    }

    //mover de uma vez até o ponto final faz sentido? --nao

    @Override
    public Rover execute(Long planetId, Long plateauId, Long roverId, String roverInstructions) {

        colideWithAnotherRoverValidation();

        var rover = findRoverByIdOutBound.execute(roverId);

        var plateau = findPlateauByIdOutbound.execute(plateauId);

        for (char ch : roverInstructions.toCharArray()) {

            if (ch == 'M') {
                switch (rover.getFacingSide()) {
                    case N:
                        checkIfOffPlateauLimits(plateau, rover.getXPosition(), rover.getYPosition() + 1);
                        rover = updateRoverOutbound.execute(rover);
                    case S:
                        checkIfOffPlateauLimits(plateau, rover.getXPosition(), rover.getYPosition() - 1);
                        rover = updateRoverOutbound.execute(rover);
                    case E:
                        checkIfOffPlateauLimits(plateau, rover.getXPosition() + 1, rover.getYPosition());
                        rover = updateRoverOutbound.execute(rover);
                    case W:
                        checkIfOffPlateauLimits(plateau, rover.getXPosition() - 1, rover.getYPosition());
                        rover = updateRoverOutbound.execute(rover);
                }

            } else if (ch == 'L') {
                updateRoverOutbound.execute(rover);
            } else if (ch == 'R') {
                updateRoverOutbound.execute(rover);
            }
        }
    }
}
