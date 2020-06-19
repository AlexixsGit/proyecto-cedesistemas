package co.edu.cedesistemas.ecommerce.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import co.edu.cedesistemas.ecommerce.EcommerceApp;
import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EcommerceApp.class)
@AutoConfigureMockMvc
public class StoreControllerIT {
    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper objectMapper;

    private static final String DEFAULT_NAME = "mystore";
    private static final String DEFAULT_PHONE = "0345887456";
    private static final String DEFAULT_ADDRESS_1 = "cl. 33 # 76 - 54";
    private static final String DEFAULT_ADDRESS_2 = "Ed. Almendros del Nogal";
    private static final String DEFAULT_ADDRESS_3 = "apt. 410";

    @Test
    public void testCreateStore() throws Exception {
        Store store = TestUtil.buildStore(DEFAULT_NAME, DEFAULT_PHONE, DEFAULT_ADDRESS_1,
                DEFAULT_ADDRESS_2, DEFAULT_ADDRESS_3);
        mvc.perform(post("/stores")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isCreated());
    }
}
