package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.infastructure.planet.gateway.FindPlateauByIdProvider;
import br.com.mars.exploringmars.infastructure.planet.gateway.FindRoverByIdProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExecuteRoverInstructionsTest {

    @Mock
    private List<ExecuteRoverInstructionsStrategy> executeRoverInstructionsStrategyList;

    @Mock
    private FindRoverByIdProvider findRoverByIdProvider;

    @Mock
    private FindPlateauByIdProvider findPlateauByIdProvider;

    @Test
    public void invalidRoverInstructions(){
        var planetId = 1L;
        var plateauId = 1L;
        var roverId = 1L;
        var roverInstructions = "MMMRRRLLLU";
        var target = new ExecuteRoverInstructions(executeRoverInstructionsStrategyList, findRoverByIdProvider, findPlateauByIdProvider);
        assertThrows(
                UnprocessableEntityException.class,
                () -> target.execute(planetId, plateauId, roverId, roverInstructions)
        );
    }
}