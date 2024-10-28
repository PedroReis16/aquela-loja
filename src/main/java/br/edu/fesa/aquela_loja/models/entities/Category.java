package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories", indexes = {
    @Index(name = "category_id_index", columnList = "id")
})
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private String description;

    @ManyToOne
    private List<Product> product;

    public Category(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getIdLong() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProduct() {
        return product;
    }
}
