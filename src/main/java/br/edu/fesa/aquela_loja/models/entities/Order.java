package br.edu.fesa.aquela_loja.models.entities;

import java.util.List;

import br.edu.fesa.aquela_loja.models.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders", indexes = {
    @Index(name = "idx_orders_id", columnList = "id"),
    @Index(name = "idx_orders_status", columnList = "status"),})
public class Order extends BaseEntity {

    @Column(unique = true)
    private String code;

    private OrderStatus status;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    public User user;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> products;

    public Order() {
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
