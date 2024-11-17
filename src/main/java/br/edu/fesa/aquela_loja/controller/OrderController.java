package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.models.dto.order.NewOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @PostMapping("/new")
    public ResponseEntity<Void> createNewOrder(@RequestBody NewOrderDto dto) {
        System.out.println("Pagamento: " + dto.getPayment());

        // Processa o endereço
        System.out.println("Endereço: " + dto.getAddress());

        // Processa os itens do carrinho
        System.out.println("Itens do carrinho:");
        dto.getCartItems().forEach(item -> {
            System.out.println("Produto: " + item.getName());
            System.out.println("Quantidade: " + item.getQuantity());
            System.out.println("Preço Total: " + item.getTotalPrice());
        });

        // Retorna uma resposta de sucesso
        return ResponseEntity.ok().build();
    }
}
