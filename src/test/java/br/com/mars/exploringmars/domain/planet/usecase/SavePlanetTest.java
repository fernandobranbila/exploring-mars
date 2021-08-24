package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlanetByNameOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlanetOutbound;
import br.com.mars.exploringmars.domain.planet.model.Planet;
import br.com.mars.exploringmars.domain.planet.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SavePlanetTest {

    @Mock
    private SavePlanetOutbound savePlanetOutbound;

    @Mock
    private FindPlanetByNameOutbound findPlanetByNameOutbound;

    @Test
    public void saveSuccess(){
        var name = "test";
        var expectedId = 1L;
        var planet = MockUtils.createPlanet(expectedId, name);
        var expectedResult = new Planet(expectedId, planet.getName());
        when(findPlanetByNameOutbound.execute(name)).thenReturn(null);
        when(savePlanetOutbound.execute(planet)).thenReturn(expectedResult);
        var target = new SavePlanet(savePlanetOutbound, findPlanetByNameOutbound);
        var result = target.execute(planet);
        assertNotNull(result);
        assertEquals(expectedId, expectedResult.getId());
    }

}