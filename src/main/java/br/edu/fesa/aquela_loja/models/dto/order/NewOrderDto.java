package br.edu.fesa.aquela_loja.models.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class NewOrderDto {
    private PaymentOrderDto payment;
    private AddressOrderDto address;
    private List<CartItemOrderDto> cartItems;
}

