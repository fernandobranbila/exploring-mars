package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.Planet;

public class PlanetDtoRequest {

    public Long id;

    public String name;

    public PlanetDtoRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlanetDtoRequest() {
    }

    public Planet toDomain() {
        return new Planet(
                this.id,
                this.name
        );
    }

}
