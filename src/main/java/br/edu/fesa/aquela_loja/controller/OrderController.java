package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.models.dto.cart.CartPaymentCardDto;
import br.edu.fesa.aquela_loja.models.dto.order.NewOrderDto;
import br.edu.fesa.aquela_loja.models.entity.OrderModel;
import br.edu.fesa.aquela_loja.models.enums.OrderStatusEnum;
import br.edu.fesa.aquela_loja.repository.IOrderRepository;
import br.edu.fesa.aquela_loja.service.CartService;
import br.edu.fesa.aquela_loja.service.ProductService;
import br.edu.fesa.aquela_loja.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IOrderRepository orderRepository;

    @PostMapping("/new")
    public ResponseEntity<Void> createNewOrder(@RequestBody NewOrderDto dto, HttpServletRequest request) throws JsonProcessingException {
        List<String> pIds = cartService.getCartItems(request);

        if (dto != null) {
            OrderModel order = new OrderModel();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            if (!username.contains("anonymousUser")) {
                order.setUser(userService.findUserByEmail(username));
            }

            order.setItems(productService.findByIdIn(pIds));


            order.setJsonItems(objectMapper.writeValueAsString(dto.getCartItems()));

            order.setTotalPrice(dto.getCartItems().stream()
                    .map(item -> BigDecimal.valueOf(item.getTotalPrice()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add));

            order.setStatus(OrderStatusEnum.WAITING_PAYMENT);

            order.setPaymentCardNumber(dto.getPayment().getNumber());

            order.setDestination(dto.getAddress().getLocation() + " n:" + dto.getAddress().getStreet());

            orderRepository.save(order);
        }

        // Processa o endereço
        System.out.println("Endereço: " + dto.getAddress());

        // Retorna uma resposta de sucesso
        return ResponseEntity.ok().build();
    }
}
