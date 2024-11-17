package br.edu.fesa.aquela_loja.models.dto.cart;

import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartItemDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String image;

    public static CartItemDto fromProdutc(ProductModel model) {
        return CartItemDto.builder()
                .id(model.getId())
                .name(model.getName())
                .price(model.getPrice())
                .quantity(1)
                .image(model.getImageBase64())
                .build();
    }
}
