package br.edu.fesa.aquela_loja.models.dto;

import lombok.Data;

@Data
public class NewUserAddressDto {

    private Long id;

    private String cep;

    private String addressIdentification;

    private String street;

    private String number;

    private String neighborhood;

    private String city;

    private String state;

    private String complement;

    private String reference;

}
