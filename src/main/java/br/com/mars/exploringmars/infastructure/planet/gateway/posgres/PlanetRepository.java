package br.com.mars.exploringmars.infastructure.planet.gateway.posgres;

import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlanetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends JpaRepository<PlanetEntity, Long> {

    PlanetEntity findByName(String name);
}
