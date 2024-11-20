package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable=false, updatable=false)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    private String type;

//    @ManyToOne
//    @JoinColumn(name = "ProductId")
    @OneToOne(mappedBy = "img")
    private ProductModel product;
}
