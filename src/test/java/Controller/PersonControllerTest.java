package Controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.janusz.Controller.PersonController;
import com.janusz.Main;
import com.janusz.Service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@ContextConfiguration(classes = Main.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;

    @Test
    public void getAllPersonsTest() throws Exception {

        this.mvc.perform(get("/all")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/index.jsp"));
    }

    @Test
    public void newPersonTest() throws Exception {

        this.mvc.perform(get("/newPerson")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/index.jsp"));
    }

    @Test
    public void savePersonTest() throws Exception {

        this.mvc.perform(post("/savePerson")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/index.jsp"));
    }

    @Test
    public void updatePersonTest() throws Exception {

        this.mvc.perform(get("/updatePerson").param("personId", "1")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/index.jsp"));
    }

    @Test
    public void deletePersonTest() throws Exception {

        this.mvc.perform(get("/deletePerson").param("personId", "1")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/index.jsp"));
    }


}
