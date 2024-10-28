package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products", indexes = {
    @Index(name = "product_id_index", columnList = "id"),
    @Index(name = "product_token_index", columnList = "token"),
    @Index(name = "product_name_index", columnList = "name"),
    @Index(name = "product_brand_index", columnList = "brand")
})
public class Product extends BaseEntity {

    @Column(unique = true)
    private String token;

    private String name;
    private String description;
    private double price;
    private Integer storageCount;
    private String brand;

    @ManyToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @ManyToMany(mappedBy = "products")
    private List<User> wish_list;

    @OneToMany(mappedBy = "product")
    private List<ProductProperty> properties;

    public Product() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public List<ProductProperty> getProperties() {
        return properties;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public List<User> getWishList() {
        return wish_list;
    }
}
