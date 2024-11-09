package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Table(name = "wallet")
@Entity
@NoArgsConstructor
public class PaymentCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String number;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUserModel appUser;
}
