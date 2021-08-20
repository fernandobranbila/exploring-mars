package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Rover;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RoverDtoRequest {

    public Long id;

    public Long plateauId;

    @NotNull
    public String name;

    @NotNull
    @Min(0)
    public Integer xPosition;

    @NotNull
    @Min(0)
    public Integer yPosition;

    @NotNull
    public String facingSide;

    public RoverDtoRequest(Long id, Long plateauId, String name, Integer xPosition, Integer yPosition, String facingSide) {
        this.id = id;
        this.plateauId = plateauId;
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.facingSide = facingSide;
    }

    public RoverDtoRequest() {
    }

    public Rover toDomain() {
        return new Rover(
                this.id,
                this.plateauId,
                this.name,
                this.xPosition,
                this.yPosition,
                FacingSide.valueOf(this.facingSide)
        );
    }
}
