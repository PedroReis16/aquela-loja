package br.edu.fesa.aquela_loja.models.dto;

import lombok.Data;

@Data
public class UpdateUserDto {

    private String userName;
    private String phone;
    private String gender;
    private String password;
    private String birthDate;

}
