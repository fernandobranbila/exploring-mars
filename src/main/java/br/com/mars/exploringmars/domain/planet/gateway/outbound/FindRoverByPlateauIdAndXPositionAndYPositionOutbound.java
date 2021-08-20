package br.com.mars.exploringmars.domain.planet.gateway.outbound;

import br.com.mars.exploringmars.domain.planet.model.Rover;

public interface FindRoverByPlateauIdAndXPositionAndYPositionOutbound {

    Rover execute(Long plateauId, Integer xPosition, Integer yPosition);
}
