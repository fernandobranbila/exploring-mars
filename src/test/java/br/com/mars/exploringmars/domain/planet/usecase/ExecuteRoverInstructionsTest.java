package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.domain.planet.utils.MockUtils;
import br.com.mars.exploringmars.infastructure.planet.gateway.FindPlateauByIdProvider;
import br.com.mars.exploringmars.infastructure.planet.gateway.FindRoverByIdProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExecuteRoverInstructionsTest {

    @Mock
    private FindRoverByIdProvider findRoverByIdProvider;

    @Mock
    private FindPlateauByIdProvider findPlateauByIdProvider;

    @Mock
    private List<ExecuteRoverInstructionsStrategy> executeRoverInstructionsStrategy;

    @Test
    public void invalidRoverInstructions() {
        var planetId = 1L;
        var plateauId = 1L;
        var roverId = 1L;
        var roverInstructions = "MMMRRRLLLU";
        var target = new ExecuteRoverInstructions(executeRoverInstructionsStrategy, findRoverByIdProvider, findPlateauByIdProvider);
        assertThrows(
                UnprocessableEntityException.class,
                () -> target.execute(planetId, plateauId, roverId, roverInstructions)
        );
    }

    @Test
    public void roverDoesntBelongsToPlateau() {
        var planetId = 1L;
        var plateauId = 1L;
        var roverId = 1L;
        var roverXPosition = 1;
        var roverYPosition = 1;
        var roverName = "test";
        var roverFacingSide = FacingSide.E;
        var roverInstructions = "MMMRRRLLL";
        var nonExpectedPlateauId = 999L;
        var plateauName = "plateau test";
        var plateauXSize = 1;
        var plateauYSize = 1;
        var plateau = MockUtils.createPlateau(plateauId, planetId, plateauName, plateauXSize, plateauYSize);
        when(findPlateauByIdProvider.execute(plateauId)).thenReturn(plateau);
        var rover = MockUtils.createRover(roverId, nonExpectedPlateauId, roverName, roverXPosition, roverYPosition, roverFacingSide);
        when(findRoverByIdProvider.execute(roverId)).thenReturn(rover);
        var target = new ExecuteRoverInstructions(executeRoverInstructionsStrategy, findRoverByIdProvider, findPlateauByIdProvider);
        assertThrows(
                UnprocessableEntityException.class,
                () -> target.execute(planetId, plateauId, roverId, roverInstructions)
        );
    }

    @Test
    public void plateauDoesntBelongsToPlanet() {
        var planetId = 1L;
        var plateauId = 1L;
        var roverId = 1L;
        var roverInstructions = "MMMRRRLLL";
        var nonExpectedPlanetId = 999L;
        var plateauName = "plateau test";
        var plateauXSize = 1;
        var plateauYSize = 1;
        var plateau = MockUtils.createPlateau(plateauId, nonExpectedPlanetId, plateauName, plateauXSize, plateauYSize);
        when(findPlateauByIdProvider.execute(plateauId)).thenReturn(plateau);
        var target = new ExecuteRoverInstructions(executeRoverInstructionsStrategy, findRoverByIdProvider, findPlateauByIdProvider);
        assertThrows(
                UnprocessableEntityException.class,
                () -> target.execute(planetId, plateauId, roverId, roverInstructions)
        );
    }

}