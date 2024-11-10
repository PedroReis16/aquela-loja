package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.models.dto.ProductRegDto;
import br.edu.fesa.aquela_loja.service.ImageService;
import br.edu.fesa.aquela_loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/regProduct")
    public String regProductForm(ProductRegDto productRegDto) {
        return "pages/product-form";
    }

    @PostMapping("/product/registration")
    public String registringProduct(ProductRegDto productRegDto, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            productService.createNewProduct(productRegDto, file);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Falha ao carregar imagem");
        }


        return "pages/product-form";
    }
}
