package co.edu.cedesistemas.ecommerce.controller;

import co.edu.cedesistemas.ecommerce.EcommerceApp;
import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.*;
import co.edu.cedesistemas.ecommerce.repository.*;
import co.edu.cedesistemas.ecommerce.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EcommerceApp.class)
@AutoConfigureMockMvc
public class OrderControllerIT {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testUpdateStatus() throws Exception {
        Order created = createOrder();
        mvc.perform(patch("/orders/" + created.getId() + "/status/" + Order.Status.DELIVERED.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(Order.Status.DELIVERED.name())));
    }

    private Order createOrder() {
        User user = TestUtil.buildUser("user@cedesistemas.edu.co", "User", "User");
        user = userRepository.save(user);

        Store store = TestUtil.buildStore("mystore", "0347852877", "Cr. 80 # 30 - 20",
                "Ed. Tulipanes", "Apt. 505");
        store = storeRepository.save(store);

        Product product1 = TestUtil.buildProduct("product1", "product1");
        product1 = productRepository.save(product1);

        Product product2 = TestUtil.buildProduct("product2", "product2");
        product2 = productRepository.save(product2);

        Address shippingAddress = TestUtil.buildAddress();
        shippingAddress = addressRepository.save(shippingAddress);

        Order order = TestUtil.buildOrder(store, user, shippingAddress);

        OrderItem i1 = TestUtil.buildOrderItem(order, product1, 2);
        i1 = orderItemRepository.save(i1);

        OrderItem i2 = TestUtil.buildOrderItem(order, product2, 1);
        i2 = orderItemRepository.save(i2);

        Set<OrderItem> orderItems = new HashSet<>(Arrays.asList(i1, i2));
        order.setItems(orderItems);

        return orderRepository.save(order);
    }
}
