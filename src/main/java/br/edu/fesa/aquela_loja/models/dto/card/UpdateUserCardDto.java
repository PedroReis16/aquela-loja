package br.edu.fesa.aquela_loja.models.dto.card;

import lombok.Data;

@Data
public class UpdateUserCardDto {

    public String holderName;
    public String expirationDate;
}
