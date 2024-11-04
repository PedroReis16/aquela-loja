package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userModel")
@NoArgsConstructor
public class UserModel {

    @Id
    private Long id;

    private String name;

    private String password;

//    private AddressModel address;
//
//    private WalletModel wallet;
}
