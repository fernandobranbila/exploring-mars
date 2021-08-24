package br.com.mars.exploringmars.application.planet.entrypoint;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.*;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.MoveRoverInbound;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlanetInbound;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SavePlateauInbound;
import br.com.mars.exploringmars.domain.planet.gateway.inbound.SaveRoverInbound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final SavePlanetInbound savePlanetInbound;

    private final SavePlateauInbound savePlateauInbound;

    private final SaveRoverInbound saveRoverInbound;

    private final MoveRoverInbound moveRoverInbound;

    public PlanetController(SavePlanetInbound savePlanetInbound, SavePlateauInbound savePlateauInbound, SaveRoverInbound saveRoverInbound, MoveRoverInbound moveRoverInbound) {
        this.savePlanetInbound = savePlanetInbound;
        this.savePlateauInbound = savePlateauInbound;
        this.saveRoverInbound = saveRoverInbound;
        this.moveRoverInbound = moveRoverInbound;
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
                saveRoverInbound.execute(planetId, plateauId, roverDtoRequest.toDomain())
        );
    }

    @PatchMapping(value = "/{planetId}/plateaus/{plateauId}/rovers/{roverId}/positions")
    @ResponseStatus(HttpStatus.OK)
    public RoverDtoResponse moveRover(
            @PathVariable Long planetId,
            @PathVariable Long plateauId,
            @PathVariable Long roverId,
            @Valid @RequestBody RoverInstructionsDtoRequest roverInstructionsDtoRequest
    ) {
        return RoverDtoResponse.fromDomain(
                moveRoverInbound.execute(planetId, plateauId, roverId, roverInstructionsDtoRequest.getInstructions())
        );
    }


}
