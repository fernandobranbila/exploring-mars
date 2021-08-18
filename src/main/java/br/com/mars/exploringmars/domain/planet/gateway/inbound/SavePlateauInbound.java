package br.com.mars.exploringmars.domain.planet.gateway.inbound;

import br.com.mars.exploringmars.domain.planet.model.Plateau;

public interface SavePlateauInbound {

    Plateau execute(Long planetId, Plateau plateau);
}
