package br.com.mars.exploringmars.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseTest {

    private final MockMvc mockMvc;

    public BaseTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public final ObjectMapper mapper = new ObjectMapper();

}
