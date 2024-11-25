package br.edu.fesa.aquela_loja.repository;

import br.edu.fesa.aquela_loja.models.entity.OrderModel;
import br.edu.fesa.aquela_loja.models.entity.UserModel;
import br.edu.fesa.aquela_loja.models.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderModel, Long> {

    List<OrderModel> findByUser(UserModel user);

    List<OrderModel> findByStatus(OrderStatusEnum status);

    List<OrderModel> findByUserAndStatus(UserModel user, OrderStatusEnum status);
}
