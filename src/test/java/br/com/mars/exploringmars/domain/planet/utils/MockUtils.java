package br.com.mars.exploringmars.domain.planet.utils;

import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Planet;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.domain.planet.model.Rover;

public class MockUtils {

    public static Planet createPlanet(
            Long id,
            String name
    ){
        return new Planet(
                id,
                name
        );
    }

    public static Plateau createPlateau(
            Long id,
            Long planetId,
            String name,
            Integer xSize,
            Integer ySize
    ){
        return new Plateau(
                id,
                planetId,
                name,
                xSize,
                ySize
        );
    }

    public static Rover createRover(
            Long id,
            Long plateauId,
            String name,
            Integer xPosition,
            Integer yPosition,
            FacingSide facingSide
    ){
        return new Rover(
                id,
                plateauId,
                name,
                xPosition,
                yPosition,
                facingSide
        );
    }
}
