package br.com.mars.exploringmars.domain.planet.model;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;

public class Plateau {

    private final Long id;

    private final Long planetId;

    private final String name;

    private final Integer xSize;

    private final Integer ySize;

    public Plateau(Long id, Long planetId, String name, Integer xSize, Integer ySize) {
        this.id = id;
        this.planetId = planetId;
        this.name = name;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void validateIfBelongsToPlanet(Long planetId) {
        if (!this.planetId.equals(planetId)) {
            throw new UnprocessableEntityException("Plateau doesn't belongs to this planet");
        }
    }

    public Long getId() {
        return id;
    }

    public Long getPlanetId() {
        return planetId;
    }

    public String getName() {
        return name;
    }

    public Integer getXSize() {
        return xSize;
    }

    public Integer getYSize() {
        return ySize;
    }
}
