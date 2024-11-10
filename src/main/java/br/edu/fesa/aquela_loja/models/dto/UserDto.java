package br.edu.fesa.aquela_loja.models.dto;

import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String document;
    private String gender;
    private String birthdate;
    private String phone;
    private String email;

    public UserDto(){}

    public UserDto(AppUserModel appUser){
        this.username = appUser.getUsername();
        this.document = appUser.getDocument();
        this.birthdate = appUser.getBirthdate();
        this.phone = appUser.getPhone();
        this.email = appUser.getEmail();
        this.gender = appUser.getGender();
    }
}
