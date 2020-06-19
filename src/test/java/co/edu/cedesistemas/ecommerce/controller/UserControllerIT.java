package co.edu.cedesistemas.ecommerce.controller;

import co.edu.cedesistemas.ecommerce.EcommerceApp;
import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.User;
import co.edu.cedesistemas.ecommerce.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EcommerceApp.class)
@AutoConfigureMockMvc
public class UserControllerIT {
    @Autowired
    private MockMvc mvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private UserService userService;

    private static final String DEFAULT_EMAIL_1 = "pedro.nieves@cedesistemas.edu.co";
    private static final String DEFAULT_NAME_1 = "Pedro";
    private static final String DEFAULT_LAST_NAME_1 = "Nieves";

    private static final String DEFAULT_EMAIL_2 = "maria.hoyos@cedesistemas.edu.co";
    private static final String DEFAULT_NAME_2 = "Maria";
    private static final String DEFAULT_LAST_NAME_2 = "Hoyos";

    @Test
    public void testCreateUser() throws Exception {
        User user = TestUtil.buildUser(DEFAULT_EMAIL_1, DEFAULT_NAME_1, DEFAULT_LAST_NAME_1);
        mvc.perform(post("/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());

        User found = userService.getByEmail(DEFAULT_EMAIL_1);

        assertThat(found, notNullValue());
        assertThat(found.getName(), equalTo(DEFAULT_NAME_1));
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = TestUtil.buildUser(DEFAULT_EMAIL_2, DEFAULT_NAME_2, DEFAULT_LAST_NAME_2);
        user = userService.createUser(user);

        mvc.perform(get("/users/by-email")
                .queryParam("email", DEFAULT_EMAIL_2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }
}
