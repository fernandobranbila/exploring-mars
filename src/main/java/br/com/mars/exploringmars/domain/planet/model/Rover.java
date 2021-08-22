package br.com.mars.exploringmars.domain.planet.model;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;

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

    private FacingSide findFacingSideMovingToRight() {
        switch (this.getFacingSide()) {
            case N:
                return FacingSide.E;
            case E:
                return FacingSide.S;
            case S:
                return FacingSide.W;
            case W:
                return FacingSide.N;
        }
        return null;
    }

    private FacingSide findFacingSideMovingToLeft() {
        switch (this.getFacingSide()) {
            case N:
                return FacingSide.W;
            case E:
                return FacingSide.N;
            case S:
                return FacingSide.E;
            case W:
                return FacingSide.S;
        }
        return null;
    }

    public void checkIfValidMoveOnPlateau(Plateau plateau, Integer expectedXPosition, Integer expectedYPosition) {
        if (expectedXPosition > plateau.getXSize() || expectedXPosition < 0 ||
                expectedYPosition > plateau.getYSize() || expectedYPosition < 0)
            throw new UnprocessableEntityException("Impossible rover move execution");
    }

    public void validateIfBelongsToPlateau(Long plateauId){
        if (!this.plateauId.equals(plateauId)){
            throw new UnprocessableEntityException("Rover doesn't belongs to this plateau");
        }
    }

    public FacingSide getFacingSideMovingToRight() {
        return this.findFacingSideMovingToRight();
    }

    public FacingSide getFacingSideMovingToLeft() {
        return this.findFacingSideMovingToLeft();
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
