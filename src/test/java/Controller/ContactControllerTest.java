package Controller;


import com.janusz.Controller.ContactController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.janusz.Entity.Contact;
import com.janusz.Entity.Person;
import com.janusz.Main;
import com.janusz.Service.ContactService;
import com.janusz.Service.PersonService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
@ContextConfiguration(classes = Main.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactService contactService;

    @MockBean
    private PersonService personService;

    @BeforeClass
    public static void initData(){
        Person person = new Person("Kamil", "Kowalski", 1l, true, "2000-01-01");
        Contact contact = new Contact(person, 3, "test");
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        person.setContacts(contacts);
        //personService.savePerson(person);

    }

    @Test
    public void getAllContactsTest() throws Exception {

        this.mvc.perform(get("/allContacts").param("personId", "1")).andDo(print()).
                                                andExpect(status().isOk()).
                                                andExpect(forwardedUrl("/WEB-INF/contacts.jsp"));
    }

    @Test
    public void newContactTest() throws Exception {

        this.mvc.perform(get("/newContact").param("personId", "1")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/contacts.jsp"));
    }

    @Test
    public void saveContactTest() throws Exception {

        this.mvc.perform(post("/saveContact").param("personId", "1")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/contacts.jsp"));
    }

    @Test
    public void updateContactTest() throws Exception {

        this.mvc.perform(get("/updateContact").param("personId", "1").param("contactId", "1")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/contacts.jsp"));
    }

    @Test
    public void deleteContactTest() throws Exception {

        this.mvc.perform(get("/deleteContact").param("personId", "1").param("contactId", "1")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/contacts.jsp"));
    }


}
