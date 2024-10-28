package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties", indexes = {
    @Index(name = "idx_property_description", columnList = "description"),
    @Index(name = "idx_property_reference_code", columnList = "referenceCode")})
public class Property extends BaseEntity {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenceCode;

    private String description;

    @OneToMany(mappedBy = "property")
    private List<ProductProperty> products;

    public Long getPropertyId() {
        return referenceCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductProperty> getProducts() {
        return products;
    }
}
