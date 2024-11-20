package br.edu.fesa.aquela_loja.models.dto;

import java.math.BigDecimal;

import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import lombok.Data;

@Data
public class ProductRegDto {

    private String pName;

    private BigDecimal price;

    private String brand;

    private CategoryEnum category;

    private int stockCount;

    private String description;
}
