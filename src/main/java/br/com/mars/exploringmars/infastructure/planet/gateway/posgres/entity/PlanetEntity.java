package br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity;

import br.com.mars.exploringmars.domain.planet.model.Planet;

import javax.persistence.*;
import java.util.List;

@Entity(name = "planet")
public class PlanetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planet_id_seq")
    @SequenceGenerator(name = "planet_id_seq", sequenceName = "planet_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    public PlanetEntity() {
    }

    public PlanetEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PlanetEntity fromDomain(Planet planet) {
        return new PlanetEntity(
                planet.getId(),
                planet.getName()
        );
    }

    public Planet toDomain() {
        return new Planet(
                this.id,
                this.name
        );
    }

    public Long getId() { return id; }

    public String getName() { return name; }
}
