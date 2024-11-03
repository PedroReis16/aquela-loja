package br.edu.fesa.aquela_loja.models.DTOs;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDTO {

    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone;
    private LocalDateTime birthDate;
    private UserAddressDTO addressDTO;
    private UserCardDTO cardDTO;

}
