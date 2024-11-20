package br.edu.fesa.aquela_loja.models.entity;


import br.edu.fesa.aquela_loja.models.dto.order.AddressOrderDto;
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
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private AppUserModel user;

    private String destination;

    @ManyToMany
    private List<ProductModel> items;

    private String jsonItems;

    private BigDecimal totalPrice;

    private String paymentCardNumber;

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
}
