package pl.koneckimarcin.functionsservice.athlete;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.koneckimarcin.functionsservice.athlete.repository.AthleteRepository;
import pl.koneckimarcin.functionsservice.clients.TrainingsClient;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AthleteControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AthleteRepository repository;

    @MockBean
    private TrainingsClient trainingsClient;

    private AthleteEntity athlete;

    @BeforeEach
    public void setUp() {
        athlete = new AthleteEntity();
        athlete.setFirstName("Athlete");
        athlete.setLastName("Test");
        repository.save(athlete);
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    void getByIdHttpRequest() throws Exception {

        Mockito.when(trainingsClient.getTrainingPlansByAthleteId(athlete.getId())).thenReturn(null);
        Mockito.when(trainingsClient.getTrainingRealizations(athlete.getId())).thenReturn(null);


        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/{id}", athlete.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Athlete")))
                .andExpect(jsonPath("$.lastName", is("Test")));
    }
}
