package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindRoverByPlateauIdAndXPositionAndYPositionOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SaveRoverOutbound;
import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Rover;
import br.com.mars.exploringmars.domain.planet.utils.MockUtils;
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

    @Test
    public void saveRoverSuccess() {
        var plateauId = 1L;
        var xPosition = 1;
        var yPosition = 1;
        var roverName = "rover test";
        var facingSide = FacingSide.E;
        var roverExpectedId = 1L;
        var rover = MockUtils.createRover(null, plateauId, roverName, xPosition, yPosition, facingSide);
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

        var target = new SaveRover(saveRoverOutbound, findRoverByPlateauIdAndXPositionAndYPositionOutbound);
        var result = target.execute(plateauId, rover);

        assertNotNull(result);
        assertEquals(roverExpectedId, result.getId());
    }

    @Test
    public void failSaveRoverDueOccupiedPosition() {
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

        var target = new SaveRover(saveRoverOutbound, findRoverByPlateauIdAndXPositionAndYPositionOutbound);
        assertThrows(
                UnprocessableEntityException.class,
                () -> target.execute(plateauId, rover)
        );
    }

}