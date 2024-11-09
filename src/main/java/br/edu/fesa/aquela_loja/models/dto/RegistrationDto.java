package br.edu.fesa.aquela_loja.models.dto;

import br.edu.fesa.aquela_loja.models.enums.Role;
import static br.edu.fesa.aquela_loja.models.enums.Role.USER;
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

    private Role role = USER;
}
