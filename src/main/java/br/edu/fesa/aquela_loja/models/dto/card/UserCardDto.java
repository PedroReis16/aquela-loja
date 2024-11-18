package br.edu.fesa.aquela_loja.models.dto.card;

import br.edu.fesa.aquela_loja.models.entity.UserCardModel;
import lombok.Data;

@Data
public class UserCardDto {

    public Long id;
    public String holderName;
    public String number;
    public String expirationDate;
    public String flag;

    public UserCardDto() {
    }

    public UserCardDto(UserCardModel card) {
        // this.id = card.getId();
        // this.holderName = card.getHolderName();
        // this.number = card.getNumber();
        // this.expirationDate = card.getExpirationDate();
        // this.cvv = card.getCvv();
    }
}
