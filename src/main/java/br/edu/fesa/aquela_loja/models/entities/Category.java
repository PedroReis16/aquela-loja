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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories", indexes = {
    @Index(name = "category_id_index", columnList = "id"),
    @Index(name = "category_reference_code_index", columnList = "referenceCode")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private UUID id;
    
    @Column(name = "referenceCode", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenceCode;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> product;
}
