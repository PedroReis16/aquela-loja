package br.edu.fesa.aquela_loja.models.dto.order;

import lombok.Data;

@Data
public class PaymentOrderDto {
    private String type;
    private String number;
    private String expirationDate;
}
