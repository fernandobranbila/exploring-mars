package br.com.mars.exploringmars.domain.planet.gateway.outbound;

import br.com.mars.exploringmars.domain.planet.model.Planet;

public interface SavePlanetOutbound {

    Planet execute(Planet planet);
}
