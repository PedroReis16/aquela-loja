package br.edu.fesa.aquela_loja.models.dto.order;

import lombok.Data;

@Data
public class CartItemOrderDto {
    private String name;
    private int quantity;
    private double totalPrice;
}
