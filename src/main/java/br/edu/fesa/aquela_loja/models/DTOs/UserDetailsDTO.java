package br.edu.fesa.aquela_loja.models.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsDTO {

    private String name;
    private String email;
    private String password;    
    private String cpf;
    private String phone;
    private String birthDate;
}
