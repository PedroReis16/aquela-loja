package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties", indexes = {
    @Index(name = "idx_property_name", columnList = "name")})
@AttributeOverride(name = "id", column = @Column(name = "property_id"))
public class Property extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "properties")
    private List<ProductProperty> products;

    public Long getPropertyId() {
        return id;
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
