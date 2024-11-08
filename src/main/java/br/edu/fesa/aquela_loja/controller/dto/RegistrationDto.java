package br.edu.fesa.aquela_loja.controller.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private String username;

    private String email;

    private String password;

    private String cep;

}
