package br.edu.fesa.aquela_loja.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Service
public class CartService {

    private static final String CART_COOKIE_NAME = "CartItems";  // Nome do cookie

    // Adicionar item ao carrinho (usando apenas o ID do produto)
    public void addToCart(HttpServletRequest request, HttpServletResponse response, String productId) {
        // Recupera os itens existentes do carrinho
        List<String> cartItems = getCartItems(request);

        // Adiciona o ID do produto ao carrinho
        if (!cartItems.contains(productId)) {
            cartItems.add(productId);

            // Salva os itens atualizados no cookie
            saveCartItems(response, cartItems);
        }
    }

    // Recupera os itens do carrinho a partir do cookie (agora uma lista de IDs)
    public List<String> getCartItems(HttpServletRequest request) {
        // Recupera o valor do cookie "CartItems"
        String cartJson = getCartCookie(request);

        // Se o cookie não existir ou for vazio, retorna uma lista vazia
        if (cartJson == null || cartJson.isEmpty()) {
            return new ArrayList<>();
        }

        // Desserializa o JSON (ou string) para uma lista de IDs de produtos
        return deserializeCartItems(cartJson);
    }

    // Salvar os itens do carrinho no cookie
    private void saveCartItems(HttpServletResponse response, List<String> cartItems) {
        // Serializa a lista de IDs para uma string (simplesmente uma lista separada por vírgulas)
        String cartJson = serializeCartItems(cartItems);

        // Cria um ResponseCookie com o valor serializado
        ResponseCookie cookie = ResponseCookie.from(CART_COOKIE_NAME, cartJson)
                .path("/")  // Define o caminho para o cookie (acessível em toda a aplicação)
                .maxAge(60 * 60 * 24 * 3)  // Define a duração (3 dias)
                .httpOnly(true)  // Define que o cookie não pode ser acessado via JavaScript (mais seguro)
                .build();

        // Adiciona o cookie à resposta HTTP
        response.addHeader("Set-Cookie", cookie.toString());
    }

    // Método para serializar os itens do carrinho (como uma lista de IDs de produtos)
    private String serializeCartItems(List<String> cartItems) {
        // Serializa a lista de IDs para uma string separada por um delimitador seguro, como '|'
        return String.join("|", cartItems);
    }

    // Método para desserializar os itens do carrinho
    private List<String> deserializeCartItems(String cartJson) {
        // Converte a string separada pelo delimitador '|' em uma lista de IDs
        return new ArrayList<>(Arrays.asList(cartJson.split("\\|")));  // Usando \\| para escapar o caractere
    }


    // Método para obter o valor do cookie "CartItems"
    private String getCartCookie(HttpServletRequest request) {
        // Acessa todos os cookies da requisição
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Verifica se o cookie é o de carrinho
                if (CART_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue(); // Retorna o valor do cookie
                }
            }
        }
        return null; // Retorna null caso não encontre o cookie
    }

    public void removeFromCart(String pId, HttpServletRequest request, HttpServletResponse response) {
        List<String> cartItems = getCartItems(request);

        cartItems.remove(pId);

        saveCartItems(response, cartItems);
    }

    public void removeAllFromCart(HttpServletRequest request, HttpServletResponse response) {
        List<String> cartItems = getCartItems(request);

        cartItems.clear();

        saveCartItems(response, cartItems);
    }
}
