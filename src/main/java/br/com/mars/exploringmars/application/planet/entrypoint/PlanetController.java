package br.com.mars.exploringmars.application.planet.entrypoint;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoRequest;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoResponse;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlanetInbound;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final SavePlanetInbound savePlanetInbound;

    public PlanetController(SavePlanetInbound savePlanetInbound) {
        this.savePlanetInbound = savePlanetInbound;
    }

    @PostMapping
    public PlanetDtoResponse save(@Valid @RequestBody PlanetDtoRequest planetDtoRequest) {
        return PlanetDtoResponse.fromDomain(
                savePlanetInbound.execute(
                        planetDtoRequest.toDomain()
                )
        );
    }

}
