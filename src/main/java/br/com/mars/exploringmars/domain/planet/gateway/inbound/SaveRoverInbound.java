package br.com.mars.exploringmars.domain.planet.gateway.inbound;

import br.com.mars.exploringmars.domain.planet.model.Rover;

public interface SaveRoverInbound {

    Rover execute(Long plateauId, Rover rover);
}
