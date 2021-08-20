package br.com.mars.exploringmars.application.planet.entrypoint;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoResponse;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlateauDtoResponse;
import br.com.mars.exploringmars.application.planet.entrypoint.domain.RoverDtoResponse;
import br.com.mars.exploringmars.application.utils.MockUtils;
import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Planet;
import br.com.mars.exploringmars.domain.planet.model.Plateau;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.PlanetRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.PlateauRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.RoverRepository;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlanetEntity;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.PlateauEntity;
import br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity.RoverEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PlateauRepository plateauRepository;

    @Autowired
    private RoverRepository roverRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void savePlanetSuccess() throws Exception {
        Long id = null;
        var name = "test";
        var planetDtoRequest = MockUtils.createPlanetDtoRequest(id, name);

        var planetDtoRequestAsString = objectMapper.writeValueAsString(planetDtoRequest);

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/planets")
                .content(planetDtoRequestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PlanetDtoResponse.class);
        assertNotNull(result.getId());
        assertEquals(result.getName(), name);

    }

    @Test
    public void savePlateauSuccess() throws Exception {
        var planetName = "planet test";
        var planetEntity = planetRepository.save(
                PlanetEntity.fromDomain(
                        new Planet(null, planetName)
                )
        );

        Long id = null;
        var name = "test";
        var xPosition = 1;
        var yPosition = 2;
        var plateauDtoRequest = MockUtils.createPlateauDtoRequest(
                id,
                planetEntity.getId(),
                name,
                xPosition,
                yPosition
        );

        var plateauDtoRequestAsString = objectMapper.writeValueAsString(plateauDtoRequest);

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/planets/" + planetEntity.getId() + "/plateaus")
                .content(plateauDtoRequestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PlateauDtoResponse.class);

        assertNotNull(result.getId());
        assertEquals(result.getName(), name);
        assertEquals(result.getXPosition(), xPosition);
        assertEquals(result.getYPosition(), yPosition);
        assertEquals(result.getPlanetId(), planetEntity.getId());

    }

    @Test
    public void savePlateauFailDueInvalidPosition() throws Exception {
        Long id = null;
        var name = "test";
        var xPosition = 1;
        var yPosition = -1;
        var planetEntityId = 1L;
        var plateauDtoRequest = MockUtils.createPlateauDtoRequest(
                id,
                planetEntityId,
                name,
                xPosition,
                yPosition
        );

        var plateauDtoRequestAsString = objectMapper.writeValueAsString(plateauDtoRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/planets/" + planetEntityId + "/plateaus")
                .content(plateauDtoRequestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void saveRoverSuccess() throws Exception {
        var planetName = "planet test";
        var planetEntity = planetRepository.save(
                PlanetEntity.fromDomain(
                        new Planet(null, planetName)
                )
        );

        var plateauName = "plateau test";
        var plateauXSize = 1;
        var plateauYSize = 1;

        var plateauEntity = plateauRepository.save(
                PlateauEntity.fromDomain(
                        planetEntity.getId(),
                        new Plateau(null, null, plateauName, plateauXSize, plateauYSize)
                )
        );

        var roverName = "rover test";
        var roverXPosition = 1;
        var roverYPosition = 1;
        var facingSide = FacingSide.E;
        var roverDtoRequest = MockUtils.createRoverDtoRequest(
                null,
                null,
                roverName,
                roverXPosition,
                roverYPosition,
                facingSide
        );

        var roverDtoRequestAsString = objectMapper.writeValueAsString(roverDtoRequest);
        var mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/planets/" + planetEntity.getId() + "/plateaus/" + plateauEntity.getId() + "/rovers")
                .content(roverDtoRequestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RoverDtoResponse.class);

        assertNotNull(result.getId());
        assertEquals(result.getName(), roverName);
        assertEquals(result.getXPosition(), roverXPosition);
        assertEquals(result.getYPosition(), roverYPosition);
        assertEquals(result.getFacingSide(), facingSide.getValue());
    }

    @Test
    public void saveRoverFailDuePositionOnPlateauAlreadyOccupied() throws Exception {
        var planetName = "planet test";
        var planetEntity = planetRepository.save(
                PlanetEntity.fromDomain(
                        new Planet(null, planetName)
                )
        );

        var plateauName = "plateau test";
        var plateauXSize = 1;
        var plateauYSize = 1;

        var plateauEntity = plateauRepository.save(
                PlateauEntity.fromDomain(
                        planetEntity.getId(),
                        new Plateau(null, null, plateauName, plateauXSize, plateauYSize)
                )
        );

        var roverXPosition = 1;
        var roverYPosition = 1;

        var alreadyOccupiedPlaceRover = roverRepository.save(
                new RoverEntity(
                        null,
                        plateauEntity.getId(),
                        "already occupied rover",
                        roverXPosition,
                        roverYPosition,
                        FacingSide.N
                )
        );

        var roverName = "rover test";
        var facingSide = FacingSide.E;
        var roverDtoRequest = MockUtils.createRoverDtoRequest(
                null,
                null,
                roverName,
                roverXPosition,
                roverYPosition,
                facingSide
        );

        var roverDtoRequestAsString = objectMapper.writeValueAsString(roverDtoRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/planets/" + planetEntity.getId() + "/plateaus/" + plateauEntity.getId() + "/rovers")
                .content(roverDtoRequestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}
