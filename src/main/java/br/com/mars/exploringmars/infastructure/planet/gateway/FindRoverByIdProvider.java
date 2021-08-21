package br.com.mars.exploringmars.infastructure.planet.gateway;

import br.com.mars.exploringmars.domain.exception.NotFoundException;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindRoverByIdOutBound;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.RoverRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.RoverEntity;
import org.springframework.stereotype.Component;

@Component
public class FindRoverByIdProvider implements FindRoverByIdOutBound {

    private final RoverRepository roverRepository;

    public FindRoverByIdProvider(RoverRepository roverRepository) {
        this.roverRepository = roverRepository;
    }

    @Override
    public Rover execute(Long roverId) {
        return roverRepository.findById(roverId)
                .map(RoverEntity::toDomain)
                .orElseThrow(() -> new NotFoundException("not found rover"));
    }
}
