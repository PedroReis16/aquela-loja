package br.edu.fesa.aquela_loja.controller;

import java.util.List;

import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
import br.edu.fesa.aquela_loja.models.dto.product.ProductDto;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.service.CartService;
import br.edu.fesa.aquela_loja.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RoutesController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping({"/", "/inicio", "/home"})
    public String loadPage(ModelMap model, @PathVariable(required = false) String page) {
        List<ProductDto> products = productService.findAllLimit10();
        model.addAttribute("products", products);

        List<ProductModel> process = productService.find10ByCategory(CategoryEnum.PROCESSADORES);
        model.addAttribute("process", process);

        List<ProductModel> phone = productService.find10ByCategory(CategoryEnum.FONE_DE_OUVIDO);
        model.addAttribute("phone", phone);

        return "pages/index";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        if (!userName.contains("anonymousUser")) {
            return "redirect:/";
        }
        return "pages/login";
    }

    @GetMapping("/cadastro")
    public String cadastrar(NewUserDto newUserDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        if (!userName.contains("anonymousUser")) {
            return "redirect:/";
        }

        return "pages/cadastro";
    }

    @GetMapping("/carrinho")
    public String getMethodName(HttpServletRequest request, ModelMap model) {
        List<String> pIds = cartService.getCartItems(request);

        List<ProductModel> products = productService.findByIdIn(pIds);
        model.addAttribute("products", products);

        return "pages/carrinho";
    }

    @PostMapping("/add-Cart/{id}")
    public ResponseEntity<Void> addToCart(
            @CookieValue(value = "CartItems", defaultValue = "") String cartCookie,
            @PathVariable("id") Long pId,
            HttpServletRequest request,
            HttpServletResponse response) {

        cartService.addToCart(request, response, pId.toString());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/itens/{category}")
    public String getCategoriesProducts(@PathVariable("category") String filter, ModelMap model) {
        List<ProductModel> products = productService.getProductsForCategoryOrDepartament(filter);
        model.addAttribute("products", products);

        return "pages/product-filtered";
    }

    @GetMapping("/search")
    public String getSearchedProducts(@RequestParam String searched, ModelMap model) {
        List<ProductModel> products = productService.findProductsByNameLike(searched);

        model.addAttribute("products", products);

        return "pages/product-filtered";
    }

    @GetMapping("/administrador")
    public String getAdminPage() {
        return "pages/admin_pages/admin";
    }

}
