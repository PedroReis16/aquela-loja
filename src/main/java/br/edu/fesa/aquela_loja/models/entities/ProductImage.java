package br.edu.fesa.aquela_loja.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_images", indexes = {
    @Index(name = "idx_product_images_id", columnList = "id")
})
public class ProductImage extends BaseEntity {

    @Lob
    private byte[] image;

    private int position;

    @ManyToOne
    private Product product;

    public ProductImage() {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int order) {
        this.position = order;
    }

    public Product getProduct() {
        return product;
    }
}
