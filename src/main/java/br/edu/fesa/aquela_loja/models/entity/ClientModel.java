package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ClientModel")
@NoArgsConstructor
public class ClientModel {

    @Id
    private Long id;

    private String name;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private AddressModel address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet")
    private WalletModel wallet;
}
