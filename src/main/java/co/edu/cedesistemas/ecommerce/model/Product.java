package co.edu.cedesistemas.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@EqualsAndHashCode(of = "id")
@Entity
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Float price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
