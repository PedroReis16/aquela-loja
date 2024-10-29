package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties", indexes = {
    @Index(name = "idx_property_description", columnList = "description"),
    @Index(name = "idx_property_reference_code", columnList = "referenceCode")})
@Getter
@NoArgsConstructor
public class Property {

    @Id
    private UUID id;
    
    @Column(name = "referenceCode", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenceCode;

    @Setter
    @Column(name = "description", unique = true)
    private String description;

    @OneToMany(mappedBy = "property")
    private List<ProductProperty> products;
}
