package br.com.mars.exploringmars.infastructure.planet.gateway;

import br.com.mars.exploringmars.domain.exception.NotFoundException;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlanetByIdOutbound;
import br.com.mars.exploringmars.domain.planet.model.Planet;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.PlanetRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlanetEntity;
import org.springframework.stereotype.Component;

@Component
public class FindPlanetByIdProvider implements FindPlanetByIdOutbound {

    private final PlanetRepository planetRepository;

    public FindPlanetByIdProvider(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public Planet execute(Long planetId) {
        return planetRepository.findById(planetId)
                .map(PlanetEntity::toDomain)
                .orElseThrow(() -> new NotFoundException("planet not found"));
    }
}
