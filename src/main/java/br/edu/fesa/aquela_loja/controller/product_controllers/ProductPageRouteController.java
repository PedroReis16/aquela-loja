package br.edu.fesa.aquela_loja.controller.product_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.fesa.aquela_loja.models.dto.product.ProductDto;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.service.ProductService;

@Controller
public class ProductPageRouteController {

    @Autowired
    private ProductService productService;

    @GetMapping("/estoque")
    public String getMethodName(ModelMap model) {
        List<ProductDto> sortedProducts = productService.getAllItems();

        model.addAttribute("products", sortedProducts);

        return "pages/product_pages/storage-products";
    }

    @GetMapping("/itens/{category}")
    public String getCategoriesProducts(@PathVariable("category") String filter, ModelMap model) {
        List<ProductDto> products = productService.getProductsForCategoryOrDepartament(filter);
        model.addAttribute("products", products);

        return "pages/product_pages/product-filtered";
    }

    @GetMapping("/search")
    public String getSearchedProducts(@RequestParam String searched, ModelMap model) {
        List<ProductModel> products = productService.findProductsByNameLike(searched);

        model.addAttribute("products", products);

        return "pages/product_pages/product-filtered";
    }
}
