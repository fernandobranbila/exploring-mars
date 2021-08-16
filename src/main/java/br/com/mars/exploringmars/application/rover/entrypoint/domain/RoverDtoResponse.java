package br.com.mars.exploringmars.application.rover.entrypoint.domain;

import javax.validation.constraints.Min;

public class RoverDtoResponse {

    private String name;

    @Min(0)
    private Integer yPosition;

    @Min(0)
    private Integer xPosition;
}
