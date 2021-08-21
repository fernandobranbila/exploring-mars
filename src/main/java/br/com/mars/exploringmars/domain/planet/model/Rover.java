package br.com.mars.exploringmars.domain.planet.model;

public class Rover {

    private final Long id;

    private final Long plateauId;

    private final String name;

    private final Integer xPosition;

    private final Integer yPosition;

    private final FacingSide facingSide;

    public Rover(Long id, Long plateauId, String name, Integer xPosition, Integer yPosition, FacingSide facingSide) {
        this.id = id;
        this.plateauId = plateauId;
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.facingSide = facingSide;
    }

    public Long getId() {
        return id;
    }

    public Long getPlateauId() {
        return plateauId;
    }

    public Integer getXPosition() {
        return xPosition;
    }

    public Integer getYPosition() {
        return yPosition;
    }

    public FacingSide getFacingSide() {
        return facingSide;
    }

    public String getName() {
        return name;
    }
}
