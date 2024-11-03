package br.edu.fesa.aquela_loja.models.DTOs;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDTO {
    private String street;
    private String number;
    private Optional<String> complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private Optional<String>  reference;
}
