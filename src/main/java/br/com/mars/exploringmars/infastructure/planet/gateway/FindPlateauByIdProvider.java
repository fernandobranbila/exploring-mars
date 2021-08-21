package br.com.mars.exploringmars.infastructure.planet.gateway;

import br.com.mars.exploringmars.domain.exception.NotFoundException;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlateauByIdOutbound;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.PlateauRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlateauEntity;
import org.springframework.stereotype.Component;

@Component
public class FindPlateauByIdProvider implements FindPlateauByIdOutbound {

    private final PlateauRepository plateauRepository;

    public FindPlateauByIdProvider(PlateauRepository plateauRepository) {
        this.plateauRepository = plateauRepository;
    }

    @Override
    public Plateau execute(Long plateauId) {
        return plateauRepository.findById(plateauId)
                .map(PlateauEntity::toDomain)
                .orElseThrow(() -> new NotFoundException("not found plateau"));
    }
}
