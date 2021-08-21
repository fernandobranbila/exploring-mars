package br.com.mars.exploringmars.domain.planet.gateway.outbound;

import br.com.mars.exploringmars.domain.planet.model.Rover;

public interface UpdateRoverOutbound {

    Rover execute(Rover rover);
}
