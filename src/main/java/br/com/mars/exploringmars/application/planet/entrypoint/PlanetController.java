package br.com.mars.exploringmars.application.planet.entrypoint;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoRequest;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoResponse;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlanetInbound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final SavePlanetInbound savePlanetInbound;

    public PlanetController(SavePlanetInbound savePlanetInbound) {
        this.savePlanetInbound = savePlanetInbound;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanetDtoResponse save(@RequestBody PlanetDtoRequest planetDtoRequest) {
        return PlanetDtoResponse.fromDomain(
                savePlanetInbound.execute(planetDtoRequest.toDomain())
        );
    }

}
