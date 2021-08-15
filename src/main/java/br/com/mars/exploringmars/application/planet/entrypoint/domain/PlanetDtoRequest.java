package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.Planet;

import javax.validation.constraints.NotNull;

public class PlanetDtoRequest {

    private final Long id;

    @NotNull
    private final String name;

    public PlanetDtoRequest(Long id, String name, Integer xSize, Integer ySize) {
        this.id = id;
        this.name = name;
    }

    public Planet toDomain() {
        return new Planet(
                this.id,
                this.name
        );
    }

}
