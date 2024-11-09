package br.edu.fesa.aquela_loja.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.fesa.aquela_loja.models.dto.RegistrationDto;

@Controller
public class RoutesController {

    @GetMapping({"/", "/inicio", "/home"})
    public String loadPage(Model model, @PathVariable(required = false) String page) {
        return "pages/index";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        if (!userName.contains("anonymousUser")) {
            return "redirect:/";
        }
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

    @GetMapping("/administrador")
    public String getMethodName(@RequestParam String param) {
        return "pages/admin_pages/admin";
    }

}
