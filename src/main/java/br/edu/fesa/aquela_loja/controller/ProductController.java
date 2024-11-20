package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.models.dto.ProductRegDto;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/form")
    public String regProductForm(ProductRegDto productRegDto) {
        return "pages/product-form";
    }

    @GetMapping("/list-all")
    public String listAllProducts(ModelMap model, @RequestParam(required = false) String showRegNotification, @RequestParam(required = false) String showUptNotification) {
        List<ProductModel> products = productService.findAll();

        List<ProductModel> sortedProducts = products.stream()
                .sorted((produdc1, product2) -> produdc1.getName().compareTo(product2.getName()))
                .toList();
        model.addAttribute("products", sortedProducts);

        if("true".equals(showRegNotification)) {
            model.addAttribute("showRegNotification", true);
        }

        if("true".equals(showUptNotification)) {
            model.addAttribute("showUptNotification", true);
        }

        return "pages/product-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, ModelMap model) {
        model.addAttribute("product", productService.findById(id));
        return "pages/product-edit";
    }

    @PostMapping("/registration")
    public String registringProduct(ProductRegDto productRegDto, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, ModelMap model) {

        try {
            if (productService.exists(productRegDto.getPName().trim())) {
                model.addAttribute("productRegDto", productRegDto);
                model.addAttribute("nameError",true);

                return "pages/product-form";
            }

            productService.createNewProduct(productRegDto, file);
            redirectAttributes.addAttribute("showRegNotification", true);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Falha ao carregar imagem");
        }

        return "redirect:/product/list-all";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute ProductModel product, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            if (productService.exists(product.getName().trim()) && !productService.findById(String.valueOf(product.getId())).getName().equals(product.getName())) {
                productService.fillImage(product);
                model.addAttribute("product", product);
                model.addAttribute("nameError",true);

                return "pages/product-edit";
            }

            productService.update(product, file);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Falha ao atualizar produto");
        }
        redirectAttributes.addAttribute("showUptNotification", true);

        return "redirect:/product/list-all";
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long pId) {
        productService.deleteById(pId);
        return ResponseEntity.ok().build();
    }
}
