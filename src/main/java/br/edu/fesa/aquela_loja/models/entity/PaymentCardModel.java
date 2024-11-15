package br.edu.fesa.aquela_loja.models.entity;

import br.edu.fesa.aquela_loja.models.enums.CardTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Table(name = "wallet")
@Entity
@NoArgsConstructor
public class PaymentCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CardTypeEnum type;

    private String holderName;

    private String number;

    private String expirationDate;

    private int cvv;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUserModel appUser;
}
