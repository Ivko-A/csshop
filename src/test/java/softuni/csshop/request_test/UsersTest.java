package softuni.csshop.request_test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class UsersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "ivan", roles = {"USER"})
    public void testUsersForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
                .andExpect(status().isForbidden());
    }

 }
