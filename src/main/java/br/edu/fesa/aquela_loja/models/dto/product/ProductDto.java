package br.edu.fesa.aquela_loja.models.dto.product;

import java.math.BigDecimal;

import br.edu.fesa.aquela_loja.models.enums.BrandEnum;
import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BrandEnum brand;
    private CategoryEnum category;
    private BigDecimal price;
    private int stockCount;
    private String image;
}
