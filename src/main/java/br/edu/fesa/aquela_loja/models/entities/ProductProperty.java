package br.edu.fesa.aquela_loja.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_properties", indexes = {
    @Index(name = "idx_product_properties_id", columnList = "id")})
public class ProductProperty extends BaseEntity {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Property property;

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Property getProperty() {
        return property;
    }

    public Product getProduct() {
        return product;
    }
}
