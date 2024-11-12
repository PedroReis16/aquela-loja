package br.edu.fesa.aquela_loja.models.entity;

import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImageModel> img;
}
