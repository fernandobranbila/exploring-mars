package br.com.mars.exploringmars.domain.planet.gateway.outbound;

import br.com.mars.exploringmars.domain.planet.model.Plateau;

public interface FindPlateauByIdOutbound {

    Plateau execute(Long plateauId);
}
