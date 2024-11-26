package br.edu.fesa.aquela_loja.models.dto;

import lombok.Data;

@Data
public class TopProductDto {
    private String productName;
    private Integer quantity;

    public TopProductDto(String productName, Integer quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }
}

