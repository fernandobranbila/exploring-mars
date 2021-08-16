package br.com.mars.exploringmars.domain.planet.utils;

import br.com.mars.exploringmars.domain.planet.model.Planet;

public class MockUtil {

    public static Planet createPlanet(
            Long id,
            String name
    ){
        return new Planet(
                id,
                name
        );
    }
}
