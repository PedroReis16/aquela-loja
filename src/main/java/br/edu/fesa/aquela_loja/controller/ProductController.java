package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.models.dto.ProductRegDto;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.service.ImageService;
import br.edu.fesa.aquela_loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/regProduct")
    public String regProductForm(ProductRegDto productRegDto) {
        return "pages/product-form";
    }

    @GetMapping("/listProducts")
    public String listAllProducts(ModelMap model) {
        List<ProductModel> products = productService.findAll();

        List<ProductModel> sortedProducts = products.stream()
                .sorted((produdc1, product2) -> produdc1.getName().compareTo(product2.getName()))
                .toList();
        model.addAttribute("products", sortedProducts);

        return "/pages/product-list";
    }

    @PostMapping("/product/registration")
    public String registringProduct(ProductRegDto productRegDto, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            productService.createNewProduct(productRegDto, file);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Falha ao carregar imagem");
        }

        return "/pages/product-list";
    }
}
