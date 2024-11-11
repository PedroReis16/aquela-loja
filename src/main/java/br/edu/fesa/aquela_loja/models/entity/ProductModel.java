package br.edu.fesa.aquela_loja.models.entity;

import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private int qtStock;

    private String brand;

    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    private ProductImageModel img;

//    @Transient
//    private String imageBase64;
//
//    @PrePersist
//    @PostLoad
//    public void loadBase64Imagem() {
//        this.imageBase64 = Base64.getEncoder().encodeToString(img.getData());
//    }

    public String getImageBase64() {
        if (img != null && img.getData() != null) {
            // Detecta o tipo MIME da imagem
            String mimeType = detectMimeType(img.getData());
            if (mimeType != null) {
                return "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(img.getData());
            }
        }
        return null;
    }

    // Método para detectar o tipo MIME da imagem (JPG, JPEG, PNG, etc.)
    private String detectMimeType(byte[] imageData) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            return URLConnection.guessContentTypeFromStream(bais);
        } catch (IOException e) {
            e.printStackTrace();
            return null;  // Caso não consiga determinar o tipo, retorna null
        }
    }
}
