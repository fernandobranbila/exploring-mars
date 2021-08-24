package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.Plateau;

public class PlateauDtoResponse {

    private Long id;

    private Long planetId;

    private String name;

    private Integer xSize;

    private Integer ySize;

    public PlateauDtoResponse(Long id, Long planetId, String name, Integer xSize, Integer ySize) {
        this.id = id;
        this.planetId = planetId;
        this.name = name;
        this.xSize = xSize;
        this.ySize = ySize;
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

    public Integer getXPosition() { return xSize; }

    public Integer getYPosition() { return ySize; }

    public Long getPlanetId() { return planetId; }

    public void setId(Long id) { this.id = id; }

    public void setPlanetId(Long planetId) { this.planetId = planetId; }

    public void setName(String name) { this.name = name; }

    public void setXPosition(Integer xPosition) { this.xSize = xPosition; }

    public void setYPosition(Integer yPosition) { this.ySize = yPosition; }
}
