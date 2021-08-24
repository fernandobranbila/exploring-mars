package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.BadRequestException;
import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlateauInbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlanetByIdOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlateauOutbound;
import br.com.mars.exploringmars.domain.planet.model.Plateau;

import javax.inject.Named;

@Named
public class SavePlateau implements SavePlateauInbound {

    private final SavePlateauOutbound savePlateauOutbound;

    private final FindPlanetByIdOutbound findPlanetByIdOutbound;

    public SavePlateau(SavePlateauOutbound savePlateauOutbound, FindPlanetByIdOutbound findPlanetByIdOutbound) {
        this.savePlateauOutbound = savePlateauOutbound;
        this.findPlanetByIdOutbound = findPlanetByIdOutbound;
    }

    @Override
    public Plateau execute(Long planetId, Plateau plateau) {
        plateau.validatePlateauSize();
        checkIfPlanetExists(planetId);
        return savePlateauOutbound.execute(planetId,plateau);
    }

    private void checkIfPlanetExists(Long planetId) {
        if(findPlanetByIdOutbound.execute(planetId) == null)
            throw new BadRequestException("planet does not exists");
    }

}
