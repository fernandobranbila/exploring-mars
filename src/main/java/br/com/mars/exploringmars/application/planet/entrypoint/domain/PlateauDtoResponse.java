package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.Plateau;

public class PlateauDtoResponse {

    private Long id;

    private Long planetId;

    private String name;

    private Integer xPosition;

    private Integer yPosition;

    public PlateauDtoResponse(Long id, Long planetId, String name, Integer xPosition, Integer yPosition) {
        this.id = id;
        this.planetId = planetId;
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public PlateauDtoResponse() {
    }

    public static PlateauDtoResponse fromDomain(Plateau plateau){
        return new PlateauDtoResponse(
                plateau.getId(),
                plateau.getPlanetId(),
                plateau.getName(),
                plateau.getXSize(),
                plateau.getYSize()
        );
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public Integer getXPosition() { return xPosition; }

    public Integer getYPosition() { return yPosition; }

    public Long getPlanetId() { return planetId; }

    public void setId(Long id) { this.id = id; }

    public void setPlanetId(Long planetId) { this.planetId = planetId; }

    public void setName(String name) { this.name = name; }

    public void setXPosition(Integer xPosition) { this.xPosition = xPosition; }

    public void setYPosition(Integer yPosition) { this.yPosition = yPosition; }
}
