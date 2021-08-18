package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.Planet;

public class PlanetDtoResponse {

    private Long id;

    private String name;

    public PlanetDtoResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlanetDtoResponse() {
    }

    public static PlanetDtoResponse fromDomain(Planet planet) {
        return new PlanetDtoResponse(
                planet.getId(),
                planet.getName()
        );
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }
}
