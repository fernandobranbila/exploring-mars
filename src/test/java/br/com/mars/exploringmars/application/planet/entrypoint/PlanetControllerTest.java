package br.com.mars.exploringmars.application.planet.entrypoint;

import br.com.mars.exploringmars.application.planet.entrypoint.domain.PlanetDtoResponse;
import br.com.mars.exploringmars.utils.MockUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.registerModule(new JavaTimeModule());
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
}
