package br.edu.fesa.aquela_loja.models.dto;

import br.edu.fesa.aquela_loja.models.entity.PaymentCardModel;
import lombok.Data;

@Data
public class UserCardDto {

    public Long id;
    public String holderName;
    public String number;
    public String expirationDate;
    public int cvv;

    public UserCardDto() {
    }

    public UserCardDto(PaymentCardModel card) {
        this.id = card.getId();
        this.holderName = card.getHolderName();
        this.number = card.getNumber();
        this.expirationDate = card.getExpirationDate();
        this.cvv = card.getCvv();
    }
}
