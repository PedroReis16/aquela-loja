package br.edu.fesa.aquela_loja.models.entities;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.fesa.aquela_loja.models.helpers.Converters.CryptoConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_users_id", columnList = "id"),
    @Index(name = "idx_users_email", columnList = "email"),
    @Index(name = "idx_users_cpf", columnList = "cpf"),})
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private UUID id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email", unique = true)
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "birthDate")
    private LocalDateTime birthDate;
    
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    
    @Column(name = "rg")
    private String rg;
    
    @Column(name = "cpf", unique = true)
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
}
