package br.edu.fesa.aquela_loja.models.dto;

import br.edu.fesa.aquela_loja.models.entity.UserModel;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String document;
    private String gender;
    private String birthDate;
    private String phone;
    private String email;

    public UserDto(){}

    public UserDto(UserModel appUser){
        this.id = appUser.getUserId();
        this.userName = appUser.getUsername();
        this.document = appUser.getDocument();
        this.birthDate = appUser.getBirthdate();
        this.phone = appUser.getPhone();
        this.email = appUser.getEmail();
        this.gender = appUser.getGender();
    }
}
