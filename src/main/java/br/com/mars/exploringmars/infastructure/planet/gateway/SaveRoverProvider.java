package br.com.mars.exploringmars.infastructure.planet.gateway;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.SaveRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.RoverRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.RoverEntity;
import org.springframework.stereotype.Component;

@Component
public class SaveRoverProvider implements SaveRoverOutbound {

    private final RoverRepository roverRepository;

    public SaveRoverProvider(RoverRepository roverRepository) {
        this.roverRepository = roverRepository;
    }

    @Override
    public Rover execute(Long plateauId, Rover rover) {
        return roverRepository.save(
                RoverEntity.fromDomain(plateauId, rover)
        ).toDomain();
    }
}
