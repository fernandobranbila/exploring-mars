package br.com.mars.exploringmars.infastructure.planet.gateway.posgres;

import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlateauEntity;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.RoverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoverRepository extends JpaRepository<RoverEntity, Long> {

    @Query(" from rover r where " +
            " r.plateauId = :plateauId and " +
            " r.xPosition = :xPosition and " +
            " r.yPosition = :yPosition ")
    RoverEntity findByPlateauIdAndXPositionAndYPosition(Long plateauId, Integer xPosition, Integer yPosition);
}
