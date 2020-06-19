package co.edu.cedesistemas.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@EqualsAndHashCode(of = "id")
@Entity
public class Address {
    @Id
    private String id;
    private String name;
    private String description;
    private String city;
    private String countryISOCode;
    private String regionISOCode;
    private String phoneNumber;
    private String street1;
    private String street2;
    private String street3;
    private String zip;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryISOCode() {
        return countryISOCode;
    }

    public void setCountryISOCode(String countryISOCode) {
        this.countryISOCode = countryISOCode;
    }

    public String getRegionISOCode() {
        return regionISOCode;
    }

    public void setRegionISOCode(String regionISOCode) {
        this.regionISOCode = regionISOCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getStreet3() {
        return street3;
    }

    public void setStreet3(String street3) {
        this.street3 = street3;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
