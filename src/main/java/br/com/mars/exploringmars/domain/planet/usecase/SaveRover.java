package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SaveRoverInbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindRoverByPlateauIdAndXPositionAndYPositionOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SaveRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.Rover;

import javax.inject.Named;

@Named
public class SaveRover implements SaveRoverInbound {

    private final SaveRoverOutbound saveRoverOutbound;

    private final FindRoverByPlateauIdAndXPositionAndYPositionOutbound findRoverByPlateauIdAndXPositionAndYPositionOutbound;

    public SaveRover(SaveRoverOutbound saveRoverOutbound, FindRoverByPlateauIdAndXPositionAndYPositionOutbound findRoverByPlateauIdAndXPositionAndYPositionOutbound) {
        this.saveRoverOutbound = saveRoverOutbound;
        this.findRoverByPlateauIdAndXPositionAndYPositionOutbound = findRoverByPlateauIdAndXPositionAndYPositionOutbound;
    }

    @Override
    public Rover execute(Long plateauId, Rover rover) {
        checkValidPositioning(plateauId, rover);
        return saveRoverOutbound.execute(plateauId, rover);
    }

    private void checkValidPositioning(Long plateauId, Rover rover) {
        if(findRoverByPlateauIdAndXPositionAndYPositionOutbound.execute(
                plateauId,
                rover.getXPosition(),
                rover.getYPosition()
        ) != null){
            System.out.println("a");
            throw new UnprocessableEntityException("There's already a rover on this place");
        }

    }


}
