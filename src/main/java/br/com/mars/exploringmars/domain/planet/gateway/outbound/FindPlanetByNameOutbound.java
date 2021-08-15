package br.com.mars.exploringmars.domain.planet.gateway.outbound;

import br.com.mars.exploringmars.domain.planet.model.Planet;

public interface FindPlanetByNameOutbound {

    Planet execute(String name);
}
