package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.exception.BadRequestException;
import br.com.mars.exploringmars.domain.exception.NotFoundException;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlanetByIdOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlateauOutbound;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.domain.planet.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SavePlateauTest {

    @Mock
    private SavePlateauOutbound savePlateauOutbound;

    @Mock
    private FindPlanetByIdOutbound findPlanetByIdOutbound;

    @Test
    public void savePlateauSuccess() {
        var planetId = 1L;
        var plateauId = 1L;
        var plateauExpectedId = 1L;
        var plateauName = "plateau test";
        var xSize = 1;
        var ySize = 1;

        var plateau = MockUtils.createPlateau(
                plateauId,
                planetId,
                plateauName,
                xSize,
                ySize
        );

        when(savePlateauOutbound.execute(planetId, plateau)).thenReturn(
                new Plateau(
                        plateauExpectedId,
                        planetId,
                        plateauName,
                        xSize,
                        ySize
                )
        );

        var target = new SavePlateau(savePlateauOutbound, findPlanetByIdOutbound);
        var result = target.execute(planetId, plateau);
        assertNotNull(result);
        assertEquals(plateau.getId(), plateauExpectedId);
    }

    @Test
    public void savePlateauFailDueInvalidSize() {
        var planetId = 1L;
        var plateauId = 1L;
        var plateauName = "plateau test";
        var xSize = -1;
        var ySize = 1;

        var plateau = MockUtils.createPlateau(
                plateauId,
                planetId,
                plateauName,
                xSize,
                ySize
        );

        var target = new SavePlateau(savePlateauOutbound, findPlanetByIdOutbound);
        assertThrows(
                BadRequestException.class,
                () -> target.execute(planetId, plateau)
        );

    }

    @Test
    public void savePlateauFailDueInvalidPlanet() {
        var planetId = 1L;
        var plateauId = 1L;
        var plateauName = "plateau test";
        var xSize = 1;
        var ySize = 1;

        var plateau = MockUtils.createPlateau(
                plateauId,
                planetId,
                plateauName,
                xSize,
                ySize
        );

        when(findPlanetByIdOutbound.execute(planetId)).thenReturn(null);

        var target = new SavePlateau(savePlateauOutbound, findPlanetByIdOutbound);
        assertThrows(
                BadRequestException.class,
                () -> target.execute(planetId, plateau)
        );

    }


}