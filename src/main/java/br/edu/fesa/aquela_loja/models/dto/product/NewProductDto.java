package br.edu.fesa.aquela_loja.models.dto.product;

import java.math.BigDecimal;

import br.edu.fesa.aquela_loja.models.enums.BrandEnum;
import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import lombok.Data;

@Data
public class NewProductDto {

    private String name;

    private BigDecimal price;

    private BrandEnum brand;

    private CategoryEnum category;

    private int stockCount;

    // private String description;
    private String image;
}
