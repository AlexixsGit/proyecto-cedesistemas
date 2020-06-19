package co.edu.cedesistemas.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(of = "id")
@Entity(name = "commerce_order")
public class Order {
    @Id
    private String id;
    @OneToOne
    private User user;
    @OneToOne
    private Store store;
    private LocalDateTime createdAt;
    @OneToOne
    private Address shippingAddress;
    @OneToMany
    private Set<OrderItem> items;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        CREATED,
        ACCEPTED,
        CONFIRMED,
        CANCELLED,
        SHIPPED,
        DELIVERED
    }
}
