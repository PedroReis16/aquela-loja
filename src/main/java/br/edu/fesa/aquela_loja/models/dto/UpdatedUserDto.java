package br.edu.fesa.aquela_loja.models.dto;

import lombok.Data;

@Data
public class UpdatedUserDto {

    private Long id;
    private String userName;
    private String email;
    private String birthDate;
    private String document;
    private String gender;
    private String phone;

    public UpdatedUserDto() {
    }

    public UpdatedUserDto(UserDto user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate();
        this.phone = user.getPhone();
        this.document = user.getDocument();
        this.gender = user.getGender();
    }
}
