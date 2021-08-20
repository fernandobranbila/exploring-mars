package br.com.mars.exploringmars.application.utils;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoRequest;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlateauDtoRequest;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.RoverDtoRequest;
import br.com.mars.exploringmars.domain.planet.model.FacingSide;

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

    public static PlateauDtoRequest createPlateauDtoRequest(
            Long id,
            Long planetId,
            String name,
            Integer xPosition,
            Integer yPosition

    ) {
        return new PlateauDtoRequest(
                id,
                planetId,
                name,
                xPosition,
                yPosition
        );
    }

    public static RoverDtoRequest createRoverDtoRequest(
            Long id,
            Long plateauId,
            String name,
            Integer xPosition,
            Integer yPosition,
            FacingSide facingSide
    ) {
        return new RoverDtoRequest(
                id,
                plateauId,
                name,
                xPosition,
                yPosition,
                facingSide.toString()
        );
    }

}
