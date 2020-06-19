package co.edu.cedesistemas.ecommerce.controller;

import co.edu.cedesistemas.ecommerce.model.Order;
import co.edu.cedesistemas.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PatchMapping("/orders/{id}/status/{status}")
    public Order updateStatus(@PathVariable("id") String id, @PathVariable("status") String status) {
        Order foundOrder = orderService.getById(id);
        foundOrder.setStatus(Order.Status.valueOf(status));
        return foundOrder;
    }
}
