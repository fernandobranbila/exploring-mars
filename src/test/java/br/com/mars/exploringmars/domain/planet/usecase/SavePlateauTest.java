package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlateauOutbound;
import br.com.mars.exploringmars.domain.planet.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SavePlateauTest {

    @Mock
    private SavePlateauOutbound savePlateauOutbound;

    @Test
    public void savePlateauSuccess(){
        var planetId = 1L;
        var plateauId = 1L;
        var plateauName = "plateau test";
        var xSize = 1;
        var ySize = 1;

        MockUtils.createPlateau(
                plateauId,
                planetId,
                plateauName,
                xSize,
                ySize
        );

        var target = new SavePlateau(savePlateauOutbound);
    }



}