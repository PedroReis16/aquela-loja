package br.edu.fesa.aquela_loja.models.dto;

import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRegDto {

    private String pName;

    private BigDecimal price;

    private String brand;

    private CategoryEnum category;

    private int qtdStock;

    private String description;
}
