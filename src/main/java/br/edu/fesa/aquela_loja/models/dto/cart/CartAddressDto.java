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
public class CartAddressDto {

    private String title;
    private String street;
    private String number;
    private String location;

    public static List<CartAddressDto> fromUser(UserModel user) {

        return user.getAddress().stream().map(address -> CartAddressDto.builder()
                .title(address.getAddressIdentification())
                .number(address.getNumber())
                .street(address.getStreet())
                .location(address.getState() + " - CEP:" + address.getCep())
                .build()).collect(Collectors.toList());
    }
}
