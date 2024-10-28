package br.edu.fesa.aquela_loja.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_addresses", indexes = {
    @Index(name = "idx_user_addresses_id", columnList = "id"),
    @Index(name = "idx_user_addresses_postal_code", columnList = "postalCode")
})
public class UserAddress extends BaseEntity {

    private String number;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    @Column(nullable = true)
    private String complement;
    @Column(nullable = true)
    private String addressReference;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserAddress() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getAddressReference() {
        return addressReference;
    }

    public void setAddressReference(String addressReference) {
        this.addressReference = addressReference;
    }

    public User getUser() {
        return user;
    }
}
