package br.com.mars.exploringmars.domain.planet.utils;

import br.com.mars.exploringmars.domain.planet.model.Planet;
import br.com.mars.exploringmars.domain.planet.model.Plateau;

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
}
