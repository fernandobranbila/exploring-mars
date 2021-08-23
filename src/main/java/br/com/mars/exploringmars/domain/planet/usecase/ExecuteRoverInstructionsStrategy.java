package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.domain.planet.model.Rover;

public interface ExecuteRoverInstructionsStrategy {

    Boolean filterInstruction(char instruction, FacingSide roverFacingSide);

    Rover execute(Plateau plateau, Rover rover);

}
