package br.com.mars.exploringmars.domain.planet.usecase;

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
        return savePlateauOutbound.execute(planetId,plateau);
    }
}
