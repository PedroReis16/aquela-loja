package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import br.edu.fesa.aquela_loja.models.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders", indexes = {
    @Index(name = "idx_orders_id", columnList = "id"),
    @Index(name = "idx_orders_status", columnList = "status"),})
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    private UUID id;
    
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    public User user;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> products;
}
