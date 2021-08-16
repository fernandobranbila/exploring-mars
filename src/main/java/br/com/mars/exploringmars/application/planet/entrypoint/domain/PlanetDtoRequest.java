package br.com.mars.exploringmars.application.planet.entrypoint.domain;

import br.com.mars.exploringmars.domain.planet.model.Planet;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class PlanetDtoRequest {

    public Long id;

    public String name;

    public PlanetDtoRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlanetDtoRequest() {
    }

    public Planet toDomain() {
        return new Planet(
                this.id,
                this.name
        );
    }

}
