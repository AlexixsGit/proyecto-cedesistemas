package co.edu.cedesistemas.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@EqualsAndHashCode(of = "id")
@Entity
public class OrderItem {
    @Id
    private String id;
    @OneToOne
    private Product product;
    private String orderId;
    private String name;
    private Float finalPrice;
    private Integer quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
