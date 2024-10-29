package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products", indexes = {
    @Index(name = "product_id_index", columnList = "id"),
    @Index(name = "product_token_index", columnList = "token"),
    @Index(name = "product_name_index", columnList = "name"),
    @Index(name = "product_brand_index", columnList = "brand")
})
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private UUID id;
    
    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "storageCount")
    private Integer storageCount;
    
    @Column(name = "brand")
    private String brand;

    @ManyToOne
    @JoinColumn(name = "category_reference_code")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @ManyToMany(mappedBy = "products")
    private List<User> wishList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductProperty> properties;
}
