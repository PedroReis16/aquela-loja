package br.edu.fesa.aquela_loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.fesa.aquela_loja.models.dto.cart.CartAddressDto;
import br.edu.fesa.aquela_loja.models.dto.cart.CartItemDto;
import br.edu.fesa.aquela_loja.models.dto.cart.CartPaymentCardDto;
import br.edu.fesa.aquela_loja.service.CartService;
import br.edu.fesa.aquela_loja.service.ProductService;
import br.edu.fesa.aquela_loja.service.user_services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItemDto>> getCartItems(HttpServletRequest request)  {
        List<CartItemDto> items = productService.getCartDto(cartService.getCartItems(request));

        return ResponseEntity.ok(items);
    }

    @GetMapping("/cart-addresses")
    public ResponseEntity<List<CartAddressDto>> getUserAddresses(HttpServletRequest request)  {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (!username.contains("anonymousUser")) {
            List<CartAddressDto> addressDtos = CartAddressDto.fromUser(userService.findUserByEmail(username));

            return ResponseEntity.ok(addressDtos);
        }

        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/cart-payCards")
    public ResponseEntity<List<CartPaymentCardDto>> getUserPayCards(HttpServletRequest request)  {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (!username.contains("anonymousUser")) {
            List<CartPaymentCardDto> cardsDtos = CartPaymentCardDto.fromUser(userService.findUserByEmail(username));

            return ResponseEntity.ok(cardsDtos);
        }

        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/cart-auth")
    public String getCartAuthPage() {
        return "pages/carrinho-auth";
    }

    @PostMapping("/remove/cart-items/{productId}")
    public ResponseEntity<Void> removeItemFromCookie(@PathVariable("productId") String pId, HttpServletRequest request, HttpServletResponse response) {
        cartService.removeFromCart(pId, request, response);

        return ResponseEntity.ok().build();
    }
}
