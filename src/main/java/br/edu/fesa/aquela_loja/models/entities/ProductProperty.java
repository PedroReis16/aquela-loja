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
@Table(name = "product_properties", indexes = {
    @Index(name = "idx_product_properties_id", columnList = "id")})
@Getter
@NoArgsConstructor
public class ProductProperty {

    @Id
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "property_reference_code")
    private Property property;

    @Setter
    @Column(name = "value")
    private String value;
}
