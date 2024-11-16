package br.edu.fesa.aquela_loja.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.fesa.aquela_loja.models.enums.DepartamentEnum;

@Controller
@RequestMapping("/header")
public class HeaderController {

    @GetMapping("/departaments")
    public ResponseEntity<Map<String, Set<String>>> getMethodName() {
        return ResponseEntity.ok(DepartamentEnum.getDepartaments());
    }

}
