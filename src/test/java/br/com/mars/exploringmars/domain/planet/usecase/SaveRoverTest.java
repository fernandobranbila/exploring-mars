package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindRoverByPlateauIdAndXPositionAndYPositionOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SaveRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.domain.planet.utils.MockUtils;
import br.com.mars.exploringmars.infastructure.planet.gateway.FindPlateauByIdProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveRoverTest {

    @Mock
    private SaveRoverOutbound saveRoverOutbound;

    @Mock
    private FindRoverByPlateauIdAndXPositionAndYPositionOutbound findRoverByPlateauIdAndXPositionAndYPositionOutbound;

    @Mock
    private FindPlateauByIdProvider findPlateauByIdProvider;

    @Test
    public void saveRoverSuccess() {
        var planetId = 1L;
        var plateauId = 1L;
        var xPosition = 1;
        var yPosition = 1;
        var roverName = "rover test";
        var facingSide = FacingSide.E;
        var roverExpectedId = 1L;
        var rover = MockUtils.createRover(null, plateauId, roverName, xPosition, yPosition, facingSide);
        var plateauName = "plateau Test";
        var plateauXSize = 1;
        var plateauYSize = 1;
        var plateau = MockUtils.createPlateau(plateauId, planetId, plateauName, plateauXSize, plateauYSize);
        when(findRoverByPlateauIdAndXPositionAndYPositionOutbound.execute(plateauId,xPosition,yPosition))
                .thenReturn(null);
        when(findPlateauByIdProvider.execute(plateauId)).thenReturn(plateau);
        when(findRoverByPlateauIdAndXPositionAndYPositionOutbound.execute(plateauId,xPosition,yPosition))
                .thenReturn(null);
        when(saveRoverOutbound.execute(plateauId, rover)).thenReturn(
                new Rover(
                        roverExpectedId,
                        plateauId,
                        roverName,
                        xPosition,
                        yPosition,
                        facingSide
                )
        );

        var target = new SaveRover(saveRoverOutbound, findRoverByPlateauIdAndXPositionAndYPositionOutbound, findPlateauByIdProvider);
        var result = target.execute(planetId, plateauId, rover);

        assertNotNull(result);
        assertEquals(roverExpectedId, result.getId());
    }

    @Test
    public void failSaveRoverDueOccupiedPosition() {
        var planetId = 1L;
        var plateauId = 1L;
        var xPosition = 1;
        var yPosition = 1;
        var roverName = "rover test";
        var facingSide = FacingSide.E;
        var alreadyOccupiedPositionRover =
                MockUtils.createRover(999L, plateauId, "alreadyOccupiedPositionRover", xPosition, yPosition, facingSide);

        var rover = MockUtils.createRover(null, plateauId, roverName, xPosition, yPosition, facingSide);
        when(findRoverByPlateauIdAndXPositionAndYPositionOutbound.execute(plateauId,xPosition,yPosition))
                .thenReturn(alreadyOccupiedPositionRover);

        var target = new SaveRover(saveRoverOutbound, findRoverByPlateauIdAndXPositionAndYPositionOutbound, findPlateauByIdProvider);
        assertThrows(
                UnprocessableEntityException.class,
                () -> target.execute(planetId, plateauId, rover)
        );
    }

    @Test
    public void failDuePlateauDoesntBelongsToPlanet() {
        var planetId = 1L;
        var plateauId = 1L;
        var xPosition = 1;
        var yPosition = 1;
        var roverName = "rover test";
        var facingSide = FacingSide.E;
        var anotherPlanetId = 999L;
        var plateauName = "plateau Test";
        var plateauXSize = 1;
        var plateauYSize = 1;
        var plateauDiffPlanet = MockUtils.createPlateau(plateauId, anotherPlanetId, plateauName, plateauXSize, plateauYSize);
        var rover = MockUtils.createRover(null, plateauId, roverName, xPosition, yPosition, facingSide);
        when(findRoverByPlateauIdAndXPositionAndYPositionOutbound.execute(plateauId,xPosition,yPosition))
                .thenReturn(null);
        when(findPlateauByIdProvider.execute(plateauId)).thenReturn(plateauDiffPlanet);

        var target = new SaveRover(saveRoverOutbound, findRoverByPlateauIdAndXPositionAndYPositionOutbound, findPlateauByIdProvider);
        assertThrows(
                UnprocessableEntityException.class,
                () -> target.execute(planetId, plateauId, rover)
        );
    }

}