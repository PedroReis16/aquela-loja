package br.edu.fesa.aquela_loja.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_addresses", indexes = {
    @Index(name = "idx_user_addresses_id", columnList = "id"),
    @Index(name = "idx_user_addresses_postal_code", columnList = "postalCode")
})
@Getter
@Setter
@NoArgsConstructor
public class UserAddress {

    @Id
    private UUID id;
    
    @Column(name = "number")
    private String number;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "postalCode")
    private String postalCode;
    
    @Column(name = "complement", nullable = true)
    private String complement;
    
    @Column(name = "reference", nullable = true)
    private String addressReference;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
