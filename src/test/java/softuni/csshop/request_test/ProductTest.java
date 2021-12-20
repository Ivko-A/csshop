package softuni.csshop.request_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProductLaptop() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/laptops"))
                .andExpect(status().isOk())
                .andExpect(view().name("laptops"))
                .andExpect(model().attributeExists("laptops"));
    }

    @Test
    @WithMockUser(username = "ivan", roles = {"USER"})
    public void testProductAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/add"))
                .andExpect(status().isForbidden());
    }

 }
