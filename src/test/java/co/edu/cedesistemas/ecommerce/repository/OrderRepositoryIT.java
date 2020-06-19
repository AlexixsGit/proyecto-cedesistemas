package co.edu.cedesistemas.ecommerce.repository;

import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderRepositoryIT {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(productRepository, notNullValue());
    }

    @Test
    public void testCreateOrder() {
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
        Order created = orderRepository.save(order);

        assertThat(created, notNullValue());
        assertThat(created.getItems(), containsInAnyOrder(i1, i2));
    }
}
