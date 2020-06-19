package co.edu.cedesistemas.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@EqualsAndHashCode(of = "id")
@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String email;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
