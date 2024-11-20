package br.edu.fesa.aquela_loja.models.dto.cart;

import br.edu.fesa.aquela_loja.models.entity.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartPaymentCardDto {

    private String number;
    private String expirationDate;

    public static List<CartPaymentCardDto> fromUser(UserModel user) {
        return user.getWallet().stream().map(card -> CartPaymentCardDto.builder()
                .number(card.getNumber())
                .expirationDate(card.getExpirationDate())
                .build()).collect(Collectors.toList());
    }
}
