package br.com.mars.exploringmars.infastructure.planet.gateway.posgres;

import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlateauEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlateauRepository extends JpaRepository<PlateauEntity, Long> {
}
