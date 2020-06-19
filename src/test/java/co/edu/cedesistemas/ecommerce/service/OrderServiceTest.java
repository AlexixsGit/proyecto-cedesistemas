package co.edu.cedesistemas.ecommerce.service;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.Address;
import co.edu.cedesistemas.ecommerce.model.Order;
import co.edu.cedesistemas.ecommerce.model.OrderItem;
import co.edu.cedesistemas.ecommerce.model.Product;
import co.edu.cedesistemas.ecommerce.model.Store;
import co.edu.cedesistemas.ecommerce.model.User;
import co.edu.cedesistemas.ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrderAndThenGetById() {
        final User user = TestUtil.buildUser("any@cedesistemas.edu.co", "John", "Doe");
        final Store store = TestUtil.buildStore("anystore", "0349856311", "any address 1",
                "any address 2", "any address 3");

        final Product p1 = TestUtil.buildProduct("product1", "product1");
        final Product p2 = TestUtil.buildProduct("product2", "product2");
        final Product p3 = TestUtil.buildProduct("product3", "product3");

        final Address shippingAddress = TestUtil.buildAddress();

        final Order order = TestUtil.buildOrder(store, user, shippingAddress);

        final OrderItem oi1 = TestUtil.buildOrderItem(order, p1, 5);
        final OrderItem oi2 = TestUtil.buildOrderItem(order, p2, 2);
        final OrderItem oi3 = TestUtil.buildOrderItem(order, p3, 3);

        Set<OrderItem> orderItems = new HashSet<>(Arrays.asList(oi1, oi2, oi3));
        order.setItems(orderItems);

        when(orderRepository.save(order)).thenReturn(order);

        Order created = orderService.createOrder(order);

        assertThat(created, notNullValue());
        assertThat(created.getItems(), containsInAnyOrder(oi1, oi2, oi3));
        assertThat(created.getStatus(), equalTo(Order.Status.CREATED));
        assertThat(created.getStore(), equalTo(store));
        assertThat(created.getUser(), equalTo(user));

        String id = created.getId();

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        Order found = orderService.getById(id);

        assertThat(found, notNullValue());
        assertThat(found.getStatus(), equalTo(Order.Status.CREATED));
    }

    @Test
    public void testGetByStatus() {
        final Store store = TestUtil.buildStore("anystore", "0349856311", "any address 1",
                "any address 2", "any address 3");
        final User user1 = TestUtil.buildUser("any@cedesistemas.edu.co", "John", "Doe");
        final User user2 = TestUtil.buildUser("other@cedesistemas.edu.co", "Mary", "Fitcher");

        final Product p1 = TestUtil.buildProduct("product1", "product1");
        final Product p2 = TestUtil.buildProduct("product2", "product2");
        final Product p3 = TestUtil.buildProduct("product3", "product3");
        final Product p4 = TestUtil.buildProduct("product3", "product3");

        final Address shippingAddress = TestUtil.buildAddress();

        final Order order1 = TestUtil.buildOrder(store, user1, shippingAddress);
        final Order order2 = TestUtil.buildOrder(store, user2, shippingAddress);

        final OrderItem oi1 = TestUtil.buildOrderItem(order1, p1, 5);
        final OrderItem oi2 = TestUtil.buildOrderItem(order1, p2, 2);

        final OrderItem oi3 = TestUtil.buildOrderItem(order2, p3, 2);
        final OrderItem oi4 = TestUtil.buildOrderItem(order2, p4, 4);

        Set<OrderItem> orderItems1 = new HashSet<>(Arrays.asList(oi1, oi2));
        order1.setItems(orderItems1);

        Set<OrderItem> orderItems2 = new HashSet<>(Arrays.asList(oi3, oi4));
        order2.setItems(orderItems2);

        when(orderRepository.save(order1)).thenReturn(order1);
        when(orderRepository.save(order2)).thenReturn(order2);

        Order created1 = orderService.createOrder(order1);
        Order created2 = orderService.createOrder(order2);

        Set<Order> orders = new HashSet<>(Arrays.asList(created1, created2));

        when(orderRepository.findByStatus(Order.Status.CREATED)).thenReturn(orders);

        Set<Order> result = orderService.getByStatus(Order.Status.CREATED);

        assertThat(result, is(not(empty())));
        assertThat(result, containsInAnyOrder(created1, created2));
    }
}
