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

        this.id = card.getId();
        this.holderName = card.getHolderName();
        this.number = "**** **** **" + card.getNumber().substring(12);
        this.expirationDate = card.getExpirationDate();
        this.flag = setCardFlag(card.getNumber());
        // this.cvv = card.getCvv();
    }

    private String setCardFlag(String cardNumber) {
        if (cardNumber.startsWith("4")) {
            return "Visa";
        } else if (cardNumber.startsWith("5")) {
            return "MasterCard";
        } else if (cardNumber.startsWith("3")) {
            if (cardNumber.startsWith("34") || cardNumber.startsWith("37")) {
                return "American Express";
            }
        } else if (cardNumber.startsWith("6")) {
            return "Discover";
        }
        return "Desconhecido";
    }
}
