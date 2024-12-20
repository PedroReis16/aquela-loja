package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.models.dto.order.CartItemOrderDto;
import br.edu.fesa.aquela_loja.models.dto.order.NewOrderDto;
import br.edu.fesa.aquela_loja.models.entity.OrderModel;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.models.enums.OrderStatusEnum;
import br.edu.fesa.aquela_loja.repository.IOrderRepository;
import br.edu.fesa.aquela_loja.service.CartService;
import br.edu.fesa.aquela_loja.service.OrderService;
import br.edu.fesa.aquela_loja.service.ProductService;
import br.edu.fesa.aquela_loja.service.user_services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

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

    @Autowired
    private OrderService orderService;

    @SuppressWarnings("null")
    @PostMapping("/new")
    public ResponseEntity<String> createNewOrder(@RequestBody NewOrderDto dto, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        List<String> pIds = cartService.getCartItems(request);
        boolean qtError = false;

        if (dto != null) {
            OrderModel order = new OrderModel();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            if (!username.contains("anonymousUser")) {
                order.setUser(userService.findUserByEmail(username));
            }

            List<ProductModel> orderItems = productService.findByIdIn(pIds);

            for (CartItemOrderDto item : dto.getCartItems()) {
                ProductModel product = productService.findByName(item.getName());

                if (product.getStockCount() > 0) {
                    if (item.getQuantity() > product.getStockCount()) {
                        return ResponseEntity.badRequest().body("Quantidade do item " + item.getName() + " excedida!!");
                    }

                    if (item.getQuantity() > 10) {
                        return ResponseEntity.badRequest()
                                .body("Quantidade do item '" + item.getName() + "' excede o limite de 10 unidades por pedido!");
                    }
                    product.setStockCount(product.getStockCount() - item.getQuantity());
                } else {
                    return ResponseEntity.badRequest().body("Produto '" + item.getName() + " indisponível!!");
                }
            }

            order.setItems(orderItems);


            order.setJsonItems(objectMapper.writeValueAsString(dto.getCartItems()));

            order.setTotalPrice(dto.getCartItems().stream()
                    .map(item -> BigDecimal.valueOf(item.getTotalPrice()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add));

            order.setStatus(OrderStatusEnum.WAITING_PAYMENT);

            order.setPaymentType(dto.getPayment().getType());
            order.setPaymentCardNumber(dto.getPayment().getNumber());

            order.setDestination(dto.getAddress().getLocation() + dto.getAddress().getStreet());

            orderRepository.save(order);

            cartService.removeAllFromCart(request, response);
        }

        // Processa o endereço
        System.out.println("Endereço: " + dto.getAddress());

        // Retorna uma resposta de sucesso
        return ResponseEntity.ok("Pedido criado com sucesso!");
    }

    @PostMapping("update-status")
    public ResponseEntity<Void> updateOrderStatus(@RequestParam Long orderId,
                                                  @RequestParam String status) {

        orderService.updateOrderStatus(orderId, status);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/filtered-products")
    public String getFilteredProducts(ModelMap model,
                                                    @RequestParam String status) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!"TODOS".equals(status)) {
            if (auth.getAuthorities().stream().toList().get(0).getAuthority().equals("ROLE_ADMIN")) {
                model.addAttribute("order", orderService.findOrdersByStatus(OrderStatusEnum.valueOf(status)));
            } else {
                model.addAttribute("order", orderService.findByClientAndStatus(userService.findUserByEmail(auth.getName()), OrderStatusEnum.valueOf(status)));
            }
        } else {
            if(auth.getAuthorities().stream().toList().get(0).getAuthority().equals("ROLE_ADMIN")) {
                model.addAttribute("order", orderService.findAll());
            } else {
                model.addAttribute("order", orderService.findOrderByClient(userService.findUserByEmail(auth.getName())));
            }
        }

        return "pages/user_pages/meus-pedidos";
    }
}
