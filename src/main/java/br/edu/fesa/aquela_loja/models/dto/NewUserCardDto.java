package br.edu.fesa.aquela_loja.models.dto;

import lombok.Data;

@Data
public class NewUserCardDto {

    public String holderName;
    public String number;
    public String expirationDate;
    public int cvv;
}
