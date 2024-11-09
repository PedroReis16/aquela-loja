package br.edu.fesa.aquela_loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.fesa.aquela_loja.controller.dto.RegistrationDto;

@Controller
public class RoutesController {
     @GetMapping({"/", "/{page}"})
    public String loadPage(Model model, @PathVariable(required = false) String page) {

        if (page == null || page.isEmpty() || !page.isEmpty() && "inicio".equals(page)) {
            return "pages/index";
        } else {
            return "pages/" + page;
        }
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    @GetMapping("/cadastro")
    public String cadastrar(RegistrationDto registrationDto) {
        return "pages/cadastro";
    }

    @GetMapping({"/usuario/{page}"})
    public String loadUserPages(Model model, @PathVariable String page) {
        return "pages/user_pages/" + page;
    }
}
