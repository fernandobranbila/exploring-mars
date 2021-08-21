package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import javax.validation.constraints.NotNull;

public class RoverInstructionsDtoRequest {

    @NotNull
    public String instructions;

    public RoverInstructionsDtoRequest(String instructions) {
        this.instructions = instructions;
    }

    public RoverInstructionsDtoRequest() {
    }

    public String getInstructions() {
        return instructions;
    }
}
