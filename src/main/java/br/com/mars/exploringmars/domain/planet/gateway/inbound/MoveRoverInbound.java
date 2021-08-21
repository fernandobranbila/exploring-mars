package br.com.mars.exploringmars.domain.planet.gateway.inbound;

import br.com.mars.exploringmars.domain.planet.model.Rover;

public interface MoveRoverInbound {

    Rover execute(Long planetId, Long plateauId, Long roverId, String roverInstructions);
}
