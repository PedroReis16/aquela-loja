package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties", indexes = {
    @Index(name = "idx_property_name", columnList = "name"),
    @Index(name = "idx_property_reference_code", columnList = "referenceCode")})
public class Property extends BaseEntity {

    @Column(unique = true)

    private Long referenceCode;

    private String description;

    @ManyToOne
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
