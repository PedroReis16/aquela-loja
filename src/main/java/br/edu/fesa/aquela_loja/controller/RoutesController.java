package br.edu.fesa.aquela_loja.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.fesa.aquela_loja.models.dto.NewUserDto;

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
    public String cadastrar(NewUserDto newUserDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        if (!userName.contains("anonymousUser")) {
            return "redirect:/";
        }

        return "pages/cadastro";
    }

    @GetMapping("/carrinho")
    public String getMethodName() {
        return "pages/carrinho";
    }

    // @GetMapping({"/usuario/{page}"})
    // public String loadUserPages(Model model, @PathVariable String page) {
    //     return "pages/user_pages/" + page;
    // }
    @GetMapping("/administrador")
    public String getAdminPage() {
        return "pages/admin_pages/admin";
    }

    @GetMapping("/user-address")
    public String getAddressPage() {
        return "pages/user-address";
    }

}
