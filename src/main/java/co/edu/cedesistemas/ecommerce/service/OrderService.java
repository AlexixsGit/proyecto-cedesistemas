package co.edu.cedesistemas.ecommerce.service;

import co.edu.cedesistemas.ecommerce.model.Order;
import co.edu.cedesistemas.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Order createOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public Order getById(String id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    public Set<Order> getByStatus(Order.Status created) {
        return this.orderRepository.findByStatus(created);
    }
}
