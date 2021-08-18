package br.com.mars.exploringmars.infastructure.planet.gateway;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlateauOutbound;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.PlateauRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlateauEntity;
import org.springframework.stereotype.Component;

@Component
public class SavePlateauProvider implements SavePlateauOutbound {

    private final PlateauRepository plateauRepository;

    public SavePlateauProvider(PlateauRepository plateauRepository) {
        this.plateauRepository = plateauRepository;
    }

    @Override
    public Plateau execute(Long planetId, Plateau plateau) {
        return plateauRepository.save(
                PlateauEntity.fromDomain(planetId, plateau)
        ).toDomain();
    }
}
