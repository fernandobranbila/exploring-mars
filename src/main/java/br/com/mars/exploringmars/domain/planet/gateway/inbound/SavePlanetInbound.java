package br.com.mars.exploringmars.domain.planet.gateway.inbound;

import br.com.mars.exploringmars.domain.planet.model.Planet;

public interface SavePlanetInbound {

    Planet execute(Planet planet);
}
