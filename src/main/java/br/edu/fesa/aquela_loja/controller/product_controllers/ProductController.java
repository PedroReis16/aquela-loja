package br.edu.fesa.aquela_loja.controller.product_controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.fesa.aquela_loja.models.dto.product.NewProductDto;
import br.edu.fesa.aquela_loja.models.dto.product.ProductDto;
import br.edu.fesa.aquela_loja.service.ProductService;

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


    @PostMapping("/new-product")
    public ResponseEntity<Void> postMethodName(@RequestBody NewProductDto newProduct) {
        try {
            productService.createNewProduct(newProduct);

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Long pId,@RequestBody ProductDto updatedProduct) {

        productService.update(pId,updatedProduct);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long pId) {
        productService.deleteById(pId);
        return ResponseEntity.ok().build();
    }
}
