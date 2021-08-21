package br.com.mars.exploringmars.infastructure.planet.gateway;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.UpdateRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.RoverRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.RoverEntity;

public class UpdateRoverProvider implements UpdateRoverOutbound {

    private final RoverRepository roverRepository;

    public UpdateRoverProvider(RoverRepository roverRepository) {
        this.roverRepository = roverRepository;
    }

    @Override
    public Rover execute(Rover rover) {
        return roverRepository.save(
                RoverEntity.fromDomainForUpdate(rover)
        ).toDomain();
    }
}
