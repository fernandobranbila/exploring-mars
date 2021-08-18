package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.BadRequestException;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlateauInbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlateauOutbound;
import br.com.mars.exploringmars.domain.planet.model.Plateau;

import javax.inject.Named;

@Named
public class SavePlateau implements SavePlateauInbound {

    private final SavePlateauOutbound savePlateauOutbound;

    public SavePlateau(SavePlateauOutbound savePlateauOutbound) {
        this.savePlateauOutbound = savePlateauOutbound;
    }

    @Override
    public Plateau execute(Long planetId, Plateau plateau) {
        validatePlateauSize(plateau);
        return savePlateauOutbound.execute(planetId,plateau);
    }

    private void validatePlateauSize(Plateau plateau) {
        if(plateau.getXPosition() < 0 || plateau.getYPosition() < 0) throw new BadRequestException("invalid size");
    }
}
