package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String document;

    private String gender;

    private String birthdate;

    private String phone;

    private String email;

    private String password;

    @OneToMany(mappedBy = "appUser",cascade = CascadeType.ALL)
    private List<AddressModel> address;

    @OneToMany(mappedBy = "appUser",cascade = CascadeType.ALL)
    private List<PaymentCardModel> wallet;
}
