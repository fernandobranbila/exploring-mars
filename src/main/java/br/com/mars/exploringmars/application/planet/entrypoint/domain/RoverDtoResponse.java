package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Rover;

public class RoverDtoResponse {

    private  Long id;

    private  Long plateauId;

    private  String name;

    private  Integer xPosition;

    private  Integer yPosition;

    private  String facingSide;

    public RoverDtoResponse(Long id, Long plateauId, String name, Integer xPosition, Integer yPosition, FacingSide facingSide) {
        this.id = id;
        this.plateauId = plateauId;
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.facingSide = facingSide.getValue();
    }

    public RoverDtoResponse() {
    }

    public static RoverDtoResponse fromDomain(Rover rover){
        return new RoverDtoResponse(
                rover.getId(),
                rover.getPlateauId(),
                rover.getName(),
                rover.getXPosition(),
                rover.getYPosition(),
                rover.getFacingSide()
        );
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlateauId(Long plateauId) {
        this.plateauId = plateauId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setXPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public void setFacingSide(String facingSide) {
        this.facingSide = facingSide;
    }

    public Long getId() {
        return id;
    }

    public Long getPlateauId() {
        return plateauId;
    }

    public String getName() {
        return name;
    }

    public Integer getXPosition() {
        return xPosition;
    }

    public Integer getYPosition() {
        return yPosition;
    }

    public String getFacingSide() {
        return facingSide;
    }
}
