package co.edu.cedesistemas.ecommerce.common;

import co.edu.cedesistemas.ecommerce.model.Address;
import co.edu.cedesistemas.ecommerce.model.Order;
import co.edu.cedesistemas.ecommerce.model.OrderItem;
import co.edu.cedesistemas.ecommerce.model.Product;
import co.edu.cedesistemas.ecommerce.model.Store;
import co.edu.cedesistemas.ecommerce.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestUtil {
    private TestUtil() {}

    public static Product buildProduct(final String name, final String description) {
        Product p = new Product();
        p.setId(UUID.randomUUID().toString());
        p.setName(name);
        p.setDescription(description);
        p.setPrice((float) Math.random() * (50000 - 20000) + 20000);
        return p;
    }

    public static Order buildOrder(Store store, User user, Address shippingAddress) {
        final Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCreatedAt(LocalDateTime.now());
        order.setShippingAddress(shippingAddress);
        order.setStatus(Order.Status.CREATED);
        order.setStore(store);
        order.setUser(user);
        return order;
    }

    public static Address buildAddress() {
        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setCity("Medellin");
        address.setCountryISOCode("CO");
        address.setRegionISOCode("ANT");
        address.setDescription("home");
        address.setName("home");
        address.setPhoneNumber("0344445878");
        address.setStreet1("cra 80 # 33 - 50");
        address.setStreet2("apto. 505");
        address.setZip("50032");
        return address;
    }

    public static User buildUser(final String email, final String name, final String lastName) {
        final User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setName(name);
        user.setLastName(lastName);
        return user;
    }

    public static Store buildStore(final String name, final String phone, final String address1, final String address2,
                                   final String address3) {
        final Store store = new Store();
        store.setId(UUID.randomUUID().toString());
        store.setName(name);
        store.setPhone(phone);
        store.setAddress1(address1);
        store.setAddress2(address2);
        store.setAddress3(address3);
        store.setCreatedAt(LocalDateTime.now());
        return store;
    }

    public static OrderItem buildOrderItem(final Order order, final Product product, final Integer quantity) {
        final OrderItem orderItem = new OrderItem();
        orderItem.setId(UUID.randomUUID().toString());
        orderItem.setName(product.getName());
        orderItem.setProduct(product);
        orderItem.setFinalPrice(product.getPrice());
        orderItem.setOrderId(order.getId());
        orderItem.setQuantity(quantity);
        return orderItem;
    }
}
