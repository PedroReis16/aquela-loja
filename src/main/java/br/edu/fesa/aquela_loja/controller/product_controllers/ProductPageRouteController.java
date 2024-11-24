package br.edu.fesa.aquela_loja.controller.product_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.service.ProductService;

@Controller
@RequestMapping("/estoque")
public class ProductPageRouteController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/", ""})
    public String getMethodName(ModelMap model) {
        List<ProductModel> products = productService.findAll();

        List<ProductModel> sortedProducts = products.stream()
                .sorted((produdc1, product2) -> produdc1.getName().compareTo(product2.getName()))
                .toList();
        model.addAttribute("products", sortedProducts);

        return "pages/product_pages/storage-products";
    }

}
