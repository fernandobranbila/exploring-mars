package br.com.mars.exploringmars.infastructure.planet.gateway;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlanetOutbound;
import br.com.mars.exploringmars.domain.planet.model.Planet;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.PlanetRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlanetEntity;
import org.springframework.stereotype.Component;

@Component
public class SavePlanetProvider implements SavePlanetOutbound {

    private final PlanetRepository planetRepository;

    public SavePlanetProvider(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public Planet execute(Planet planet) {
        return planetRepository.save(
                PlanetEntity.fromDomain(planet)
        ).toDomain();
    }

}
