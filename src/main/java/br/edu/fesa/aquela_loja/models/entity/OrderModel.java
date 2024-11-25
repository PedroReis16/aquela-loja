package br.edu.fesa.aquela_loja.models.entity;


import br.edu.fesa.aquela_loja.models.dto.order.CartItemOrderDto;
import br.edu.fesa.aquela_loja.models.enums.OrderStatusEnum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserModel user;

    private String destination;

    @ManyToMany
    private List<ProductModel> items;

    private String jsonItems;

    private BigDecimal totalPrice;

    private String paymentCardNumber;

    private String paymentType;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @CreationTimestamp
    private LocalDateTime orderedAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;


    public HashMap<String, String> getItemsQtRelation() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<CartItemOrderDto> cartItem = objectMapper.readValue(this.jsonItems, new TypeReference<List<CartItemOrderDto>>() {
            });

            HashMap<String, String> itemsQtRelation = new HashMap<>();
            cartItem.forEach(item -> itemsQtRelation.put(item.getName(), String.valueOf(item.getQuantity())));

            return itemsQtRelation;
        } catch (Exception e) {
            System.out.println("erro ao carregar items e quantidades");
        }

        return new HashMap<>();
    }

    public String getItemsRelation() {
        var mapRelation = getItemsQtRelation();

        StringBuilder builder= new StringBuilder();

        mapRelation.forEach((name, qt) -> builder.append(name).append(" - ").append(qt).append("<br>"));

        return builder.toString();
    }

    public String getFormattedOrderedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return orderedAt.format(formatter);
    }

    public String getFormattedUpdatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return updateAt.format(formatter);
    }
}
