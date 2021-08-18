package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.Plateau;

import javax.validation.constraints.Min;

public class PlateauDtoRequest {

    public Long id;

    public Long planetId;

    public String name;

    public Integer xSize;

    public Integer ySize;

    public PlateauDtoRequest(Long id, Long planetId, String name, Integer xSize, Integer ySize) {
        this.id = id;
        this.planetId = planetId;
        this.name = name;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public PlateauDtoRequest() {
    }

    public Plateau toDomain() {
        return new Plateau(
                this.id,
                this.planetId,
                this.name,
                this.xSize,
                this.ySize
        );
    }

}
