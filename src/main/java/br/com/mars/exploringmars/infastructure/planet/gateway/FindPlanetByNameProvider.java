package br.com.mars.exploringmars.infastructure.planet.gateway;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlanetByNameOutbound;
import br.com.mars.exploringmars.domain.planet.model.Planet;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.PlanetRepository;
import org.springframework.stereotype.Component;

@Component
public class FindPlanetByNameProvider implements FindPlanetByNameOutbound {

    private final PlanetRepository planetRepository;

    public FindPlanetByNameProvider(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public Planet execute(String name) {
        var planet = planetRepository.findByName(name);
        if (planet != null){
            return planet.toDomain();
        }
        return null;
    }
}
