package br.edu.fesa.aquela_loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping({"/", "/{page}"})
    public String loadPage(Model model, @PathVariable(required = false) String page) {

        if (page == null || page.isEmpty() || !page.isEmpty() && "inicio".equals(page)) {
            model.addAttribute("contentFragment", "pages/inicio");
        } else {
            model.addAttribute("contentFragment", "pages/" + page);
        }

        return "index";

    }

    @GetMapping({"/usuario/{page}"})
    public String loadUserPages(Model model, @PathVariable String page) {
        model.addAttribute("contentFragment", "pages/user_pages/" + page);
        return "index";
    }

}
