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
@Table(name = "categories", indexes = {
    @Index(name = "category_id_index", columnList = "id"),
    @Index(name = "category_reference_code_index", columnList = "referenceCode")
})
public class Category extends BaseEntity {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long referenceCode;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> product;

    public Category(Long id, String description) {
        this.referenceCode = id;
        this.description = description;
    }

    public Long getIdLong() {
        return referenceCode;
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
