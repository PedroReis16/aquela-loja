package br.edu.fesa.aquela_loja.models.entities;

import br.edu.fesa.aquela_loja.models.helpers.Converters.CryptoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_wallets", indexes = {
    @Index(name = "idx_user_wallets_id", columnList = "id")
})
@Getter
@Setter
@NoArgsConstructor
public class UserWallet {

    @Id
    private UUID id;
    
    @Column(name = "number")
    @Convert(converter = CryptoConverter.class)
    private String number;

    @Column(name = "securityCode")
    @Convert(converter = CryptoConverter.class)
    private int securityCode;

    @Column(name = "expiresAt")
    private String expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
