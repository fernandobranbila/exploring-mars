package br.com.mars.exploringmars.infastructure.planet.gateway.posgres;

import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlanetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<PlanetEntity, Long> {

    PlanetEntity findByName(String name);
}
