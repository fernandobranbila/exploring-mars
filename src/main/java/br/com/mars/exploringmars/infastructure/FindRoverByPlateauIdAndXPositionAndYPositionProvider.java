package br.com.mars.exploringmars.infastructure;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindRoverByPlateauIdAndXPositionAndYPositionOutbound;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.RoverRepository;
import org.springframework.stereotype.Component;

@Component
public class FindRoverByPlateauIdAndXPositionAndYPositionProvider implements FindRoverByPlateauIdAndXPositionAndYPositionOutbound {

    private final RoverRepository roverRepository;

    public FindRoverByPlateauIdAndXPositionAndYPositionProvider(RoverRepository roverRepository) {
        this.roverRepository = roverRepository;
    }

    @Override
    public Rover execute(Long plateauId, Integer xPosition, Integer yPosition) {
        var roverEntity = roverRepository.findByPlateauIdAndXPositionAndYPosition(plateauId, xPosition, yPosition);
        if (roverEntity != null)
            return roverEntity.toDomain();
        return null;
    }
}
