package br.com.mars.exploringmars.domain.planet.gateway.outbound;

import br.com.mars.exploringmars.domain.planet.model.Rover;

public interface FindRoverByIdOutBound {

    Rover execute(Long roverId);
}
