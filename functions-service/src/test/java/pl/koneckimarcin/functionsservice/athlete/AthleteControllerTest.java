package pl.koneckimarcin.functionsservice.athlete;

import jakarta.transaction.Transactional;
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
import pl.koneckimarcin.functionsservice.coach.CoachEntity;
import pl.koneckimarcin.functionsservice.coach.repository.CoachRepository;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class AthleteControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AthleteRepository repository;

    @Autowired
    private CoachRepository coachRepository;

    @MockBean
    private TrainingsClient trainingsClient;

    private AthleteEntity athlete;
    private AthleteEntity athleteWithCoach;

    private CoachEntity coach;

    @BeforeEach
    public void setUp() {
        athlete = new AthleteEntity();
        athlete.setFirstName("Athlete");
        athlete.setLastName("Test");
        repository.save(athlete);

        athleteWithCoach = new AthleteEntity();
        athleteWithCoach.setFirstName("Athlete");
        athleteWithCoach.setLastName("WithCoach");
        athleteWithCoach.setCoachId(1L);
        repository.save(athleteWithCoach);

        coach = new CoachEntity();
        coach.setFirstName("Coach");
        coach.setLastName("CoachTest");
        coach.setAthletes(Set.of(athleteWithCoach));
        coachRepository.save(coach);
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
        coachRepository.deleteAll();
    }

    @Test
    void getByIdHttpRequest() throws Exception {

        Mockito.when(trainingsClient.getTrainingPlansByAthleteId(athlete.getId())).thenReturn(null);
        Mockito.when(trainingsClient.getTrainingRealizations(athlete.getId())).thenReturn(null);


        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/{id}", athlete.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Athlete")))
                .andExpect(jsonPath("$.lastName", is("Test")));

        Long nonExistingId = 2L;

        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/{id}", nonExistingId))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message", is("Athlete not found with id : '"
                        + nonExistingId + "'")));
    }

    @Test
    void getByLastNameHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/athletes")
                        .param("lastname", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Athlete")))
                .andExpect(jsonPath("$[0].lastName", is("Test")));

        String nonExistingLastname = "Test2";

        mockMvc.perform(MockMvcRequestBuilders.get("/athletes")
                        .param("lastname", nonExistingLastname))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message", is("Athlete not found with lastname : '"
                        + nonExistingLastname + "'")));

    }

    @Test
    void getByCoachIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/coach")
                        .param("coachId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Athlete")))
                .andExpect(jsonPath("$[0].lastName", is("WithCoach")));

        Long nonExisitingId = 3L;

        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/coach")
                        .param("coachId", String.valueOf(nonExisitingId)))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message", is("Coach not found with id : '"
                        + nonExisitingId + "'")));
    }
}
