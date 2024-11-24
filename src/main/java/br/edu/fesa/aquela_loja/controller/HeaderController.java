package br.edu.fesa.aquela_loja.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import br.edu.fesa.aquela_loja.models.enums.DepartamentEnum;

import br.edu.fesa.aquela_loja.models.enums.BrandEnum;

@Controller
@RequestMapping("/header")
public class HeaderController {

    @GetMapping("/departaments")
    public ResponseEntity<Map<String, Set<String>>> getDepartaments() {
        return ResponseEntity.ok(DepartamentEnum.getDepartaments());
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<CategoryEnum, String>> getCategories() {
        return ResponseEntity.ok(CategoryEnum.getCategories());
    }

    @GetMapping("/brands")
    public ResponseEntity<Map<BrandEnum, String>> getBrands() {
        return ResponseEntity.ok(BrandEnum.getBrands());
    }

}
