package br.com.mars.exploringmars.application.planet.entrypoint;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoRequest;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoResponse;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlateauDtoRequest;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlateauDtoResponse;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlanetInbound;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlateauInbound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final SavePlanetInbound savePlanetInbound;

    private final SavePlateauInbound savePlateauInbound;

    public PlanetController(SavePlanetInbound savePlanetInbound, SavePlateauInbound savePlateauInbound) {
        this.savePlanetInbound = savePlanetInbound;
        this.savePlateauInbound = savePlateauInbound;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanetDtoResponse save(@Valid @RequestBody PlanetDtoRequest planetDtoRequest) {
        return PlanetDtoResponse.fromDomain(
                savePlanetInbound.execute(planetDtoRequest.toDomain())
        );
    }

    @PostMapping("/{planetId}/plateaus")
    @ResponseStatus(HttpStatus.CREATED)
    public PlateauDtoResponse savePlateau(
            @PathVariable Long planetId,
            @Valid @RequestBody PlateauDtoRequest plateauDtoRequest
    ) {
        return PlateauDtoResponse.fromDomain(
                savePlateauInbound.execute(planetId, plateauDtoRequest.toDomain())
        );
    }
    

}
