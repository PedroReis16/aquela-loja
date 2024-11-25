package br.edu.fesa.aquela_loja.controller.product_controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.fesa.aquela_loja.models.dto.product.NewProductDto;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.service.ProductService;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Void> findProductByName(@RequestParam String description) {
        boolean exists = productService.exists(description);

        if (exists) {
            return ResponseEntity.ok().build();
        } 
          return ResponseEntity.noContent().build();
        
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, ModelMap model) {
        model.addAttribute("product", productService.findById(id));
        return "pages/product-edit";
    }

    @PostMapping("/new-product")
    public ResponseEntity<Void> postMethodName(@RequestBody NewProductDto newProduct) {
       try {
        productService.createNewProduct(newProduct);
        
        
        
        return ResponseEntity.ok().build();
       } catch (Exception e) {
        return ResponseEntity.badRequest().build();
       }
    }
    
    // @PostMapping("/registration")
    // public String registringProduct(ProductRegDto productRegDto, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, ModelMap model) {

    //     try {
    //         if (productService.exists(productRegDto.getPName().trim())) {
    //             model.addAttribute("productRegDto", productRegDto);
    //             model.addAttribute("nameError", true);

    //             return "pages/product-form";
    //         }

    //         productService.createNewProduct(productRegDto, file);
    //         redirectAttributes.addAttribute("showRegNotification", true);
    //     } catch (IOException e) {
    //         redirectAttributes.addFlashAttribute("message", "Falha ao carregar imagem");
    //     }

    //     return "redirect:/product/list-all";
    // }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute ProductModel product, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            if (productService.exists(product.getName().trim()) && !productService.findById(String.valueOf(product.getId())).getName().equals(product.getName())) {
                productService.fillImage(product);
                model.addAttribute("product", product);
                model.addAttribute("nameError", true);

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
