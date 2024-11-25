package br.edu.fesa.aquela_loja.service;

import br.edu.fesa.aquela_loja.models.entity.OrderModel;
import br.edu.fesa.aquela_loja.models.entity.UserModel;
import br.edu.fesa.aquela_loja.models.enums.OrderStatusEnum;
import br.edu.fesa.aquela_loja.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private IOrderRepository repository;

    public List<OrderModel> findAll() {
        return repository.findAll();
    }

    public List<OrderModel> findOrdersByStatus(OrderStatusEnum statusEnum) {
        return repository.findByStatus(statusEnum);
    }

    public List<OrderModel> findOrderByClient(UserModel user) {
        return repository.findByUser(user);
    }

    public List<OrderModel> findByClientAndStatus(UserModel user, OrderStatusEnum statusEnum) {
        return repository.findByUserAndStatus(user, statusEnum);
    }

    public void updateOrderStatus(Long orderId, String status) {
        Optional<OrderModel> order = repository.findById(orderId);

        if (order.isPresent()) {
            OrderModel orderModel = order.get();
            orderModel.setStatus(OrderStatusEnum.valueOf(status));
            repository.save(orderModel);
        }
    }
}
