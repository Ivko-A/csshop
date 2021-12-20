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
public class ArticleTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testArticle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/article/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("article-blog"))
                .andExpect(model().attributeExists("articles"));
    }

    @Test
    @WithMockUser(username = "ivan", roles = {"USER"})
    public void testArticleAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/article/add"))
                .andExpect(status().isForbidden());
    }

 }
