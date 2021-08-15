package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlanetInbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlanetByNameOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlanetOutbound;
import br.com.mars.exploringmars.domain.planet.model.Planet;

import javax.inject.Named;

@Named
public class SavePlanet implements SavePlanetInbound {

    private final SavePlanetOutbound savePlanetOutbound;

    private final FindPlanetByNameOutbound findPlanetByNameOutbound;

    public SavePlanet(SavePlanetOutbound savePlanetOutbound, FindPlanetByNameOutbound findPlanetByNameOutbound) {
        this.savePlanetOutbound = savePlanetOutbound;
        this.findPlanetByNameOutbound = findPlanetByNameOutbound;
    }

    @Override
    public Planet execute(Planet planet) {
        if (findPlanetByNameOutbound.execute(planet.getName()) != null) {
          throw new UnprocessableEntityException("Planet with same name already exists");
        }
        return savePlanetOutbound.execute(planet);
    }
}
