package br.com.mars.exploringmars.application.rover.entrypoint.domain;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;

public class RoverDtoResponse {

    private String name;

    @Min(0)
    private Integer yPosition;

    @Min(0)
    private Integer xPosition;
}
