package br.edu.fesa.aquela_loja.models.entities;

import br.edu.fesa.aquela_loja.models.helpers.Converters.CryptoConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_wallets", indexes = {
    @Index(name = "idx_user_wallets_id", columnList = "id")
})
public class UserWallet extends BaseEntity {

    @Convert(converter = CryptoConverter.class)
    private String number;

    @Convert(converter = CryptoConverter.class)
    private int securityCode;

    private String expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserWallet() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

}
