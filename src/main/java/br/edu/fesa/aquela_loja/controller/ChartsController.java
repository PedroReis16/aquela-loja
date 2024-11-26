package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.models.dto.ChatDataDto;
import br.edu.fesa.aquela_loja.models.dto.TopProductDto;
import br.edu.fesa.aquela_loja.models.entity.OrderModel;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.service.OrderService;
import br.edu.fesa.aquela_loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chart")
public class ChartsController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/sales")
    @ResponseBody
    public List<ChatDataDto> getSalesData() {
        List<OrderModel> orders = orderService.findAll();

        // Mapeia as vendas por marca
        Map<String, Integer> salesMap = new HashMap<>();

        for (OrderModel order : orders) {
            // Obtém a relação de itens e quantidades do pedido
            Map<String, String> itemsQtRelation = order.getItemsQtRelation();

            // Para cada item no pedido, soma a quantidade à marca
            for (Map.Entry<String, String> entry : itemsQtRelation.entrySet()) {
                String itemName = entry.getKey();
                int quantity = Integer.parseInt(entry.getValue());

                // Obtém a marca do produto
                String brand = getBrandFromItemName(itemName);

                // Atualiza o total de vendas por marca
                salesMap.put(brand, salesMap.getOrDefault(brand, 0) + quantity);
            }
        }

        // Converte o Map para uma lista de DTOs
        List<ChatDataDto> salesData = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : salesMap.entrySet()) {
            salesData.add(new ChatDataDto(entry.getKey(), entry.getValue(), "2024-11-01")); // Você pode usar a data atual ou qualquer outro critério
        }

        return salesData;
    }


    private String getBrandFromItemName(String itemName) {
        // Busca o produto pelo nome
        ProductModel product = productService.findByName(itemName);

        // Verifica se o produto foi encontrado
        if (product != null) {
            // Retorna o nome da marca associada ao produto
            return product.getBrand().name();  // Usando o enum para obter o nome da marca
        } else {
            // Se o produto não for encontrado, retornamos "Desconhecido" ou qualquer valor padrão
            return "Desconhecido";
        }
    }



    @GetMapping("/show-sales")
    public String getSalesPage() {
        return "/pages/sales";
    }

    @GetMapping("/top-products")
    @ResponseBody  // Garante que o retorno será convertido para JSON
    public List<TopProductDto> getTopProducts() {
        List<OrderModel> orders = orderService.findAll();  // Obtém todos os pedidos
        Map<String, Integer> productSalesMap = new HashMap<>();

        // Percorre todos os pedidos
        for (OrderModel order : orders) {
            // Para cada item no pedido
            order.getItemsQtRelation().forEach((itemName, quantity) -> {
                productSalesMap.put(itemName, productSalesMap.getOrDefault(itemName, 0) + Integer.parseInt(quantity));
            });
        }

        // Ordena os produtos pela quantidade vendida (decrescente) e pega os 10 primeiros
        List<TopProductDto> topProducts = productSalesMap.entrySet().stream()
                .map(entry -> new TopProductDto(entry.getKey(), entry.getValue()))
                .sorted((p1, p2) -> Integer.compare(p2.getQuantity(), p1.getQuantity()))
                .limit(10)
                .collect(Collectors.toList());

        return topProducts;
    }
}
