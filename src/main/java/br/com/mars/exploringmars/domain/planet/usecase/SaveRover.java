package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SaveRoverInbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindRoverByPlateauIdAndXPositionAndYPositionOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SaveRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.infastructure.planet.gateway.FindPlateauByIdProvider;

import javax.inject.Named;

@Named
public class SaveRover implements SaveRoverInbound {

    private final SaveRoverOutbound saveRoverOutbound;

    private final FindRoverByPlateauIdAndXPositionAndYPositionOutbound findRoverByPlateauIdAndXPositionAndYPositionOutbound;

    private final FindPlateauByIdProvider findPlateauByIdProvider;

    public SaveRover(SaveRoverOutbound saveRoverOutbound, FindRoverByPlateauIdAndXPositionAndYPositionOutbound findRoverByPlateauIdAndXPositionAndYPositionOutbound, FindPlateauByIdProvider findPlateauByIdProvider) {
        this.saveRoverOutbound = saveRoverOutbound;
        this.findRoverByPlateauIdAndXPositionAndYPositionOutbound = findRoverByPlateauIdAndXPositionAndYPositionOutbound;
        this.findPlateauByIdProvider = findPlateauByIdProvider;
    }

    @Override
    public Rover execute(Long planetId, Long plateauId, Rover rover) {
        checkValidPositioning(plateauId, rover);
        var plateau = findPlateauByIdProvider.execute(plateauId);
        plateau.validateIfBelongsToPlanet(planetId);
        return saveRoverOutbound.execute(plateauId, rover);
    }

    private void checkValidPositioning(Long plateauId, Rover rover) {
        if(findRoverByPlateauIdAndXPositionAndYPositionOutbound.execute(
                plateauId,
                rover.getXPosition(),
                rover.getYPosition()
        ) != null){
            throw new UnprocessableEntityException("There's already a rover on this place");
        }

    }


}
