package br.com.mars.exploringmars.application.utils;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoRequest;

public class MockUtils {

    public static PlanetDtoRequest createPlanetDtoRequest(
            Long id,
            String name
    ) {
        return new PlanetDtoRequest(
                id,
                name
        );
    }

}
