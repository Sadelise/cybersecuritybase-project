package sec.project;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sec.project.repository.SignupRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private SignupRepository signupRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).apply(springSecurity()).build();
    }

    @WithMockUser(value = "ted")
    @Test
    public void signupAddsDataToDatabase() throws Throwable {
        mockMvc.perform(post("/form").param("name", "Testname").param("address", "Testaddress").param("creditcard", "1")).andReturn();
        assertEquals(1L, signupRepository.findAll().stream().filter(s -> s.getName().equals("Testname") && s.getAddress().equals("Testaddress")).count());
    }
}
