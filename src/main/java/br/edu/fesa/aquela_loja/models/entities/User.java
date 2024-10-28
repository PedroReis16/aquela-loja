package br.edu.fesa.aquela_loja.models.entities;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.fesa.aquela_loja.models.helpers.Converters.CryptoConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_users_id", columnList = "id"),
    @Index(name = "idx_users_email", columnList = "email"),
    @Index(name = "idx_users_cpf", columnList = "cpf"),})
public class User extends BaseEntity {

    private String name;
    private String phone;
    private LocalDateTime birthDate;
    private String rg;
    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @Convert(converter = CryptoConverter.class)
    private String cpf;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserWallet> wallet;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> addresses;

    @ManyToMany
    @JoinTable(name = "user_wish_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public User() {
    }

    public List<UserWallet> getWallet() {
        return wallet;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<UserAddress> getAddresses() {
        return addresses;
    }
}
