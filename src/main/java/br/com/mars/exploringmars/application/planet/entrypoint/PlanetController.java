package br.com.mars.exploringmars.application.planet.entrypoint;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.*;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SaveRoverInbound;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlanetInbound;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlateauInbound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final SavePlanetInbound savePlanetInbound;

    private final SavePlateauInbound savePlateauInbound;

    private final SaveRoverInbound saveRoverInbound;

    public PlanetController(SavePlanetInbound savePlanetInbound, SavePlateauInbound savePlateauInbound, SaveRoverInbound saveRoverInbound) {
        this.savePlanetInbound = savePlanetInbound;
        this.savePlateauInbound = savePlateauInbound;
        this.saveRoverInbound = saveRoverInbound;
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

    @PostMapping(value = "/{planetId}/plateaus/{plateauId}/rovers")
    @ResponseStatus(HttpStatus.CREATED)
    public RoverDtoResponse saveRover(
            @RequestBody RoverDtoRequest roverDtoRequest,
            @PathVariable Long planetId,
            @PathVariable Long plateauId
    ) {
        return RoverDtoResponse.fromDomain(
                saveRoverInbound.execute(plateauId, roverDtoRequest.toDomain())
        );
    }


}
