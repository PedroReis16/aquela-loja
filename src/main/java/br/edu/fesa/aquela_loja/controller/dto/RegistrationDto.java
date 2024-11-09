package br.edu.fesa.aquela_loja.controller.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private String username;

    private String document;

    private String gender;

    private String birthdate;

    private String phone;

    private String email;

    private String password;

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
