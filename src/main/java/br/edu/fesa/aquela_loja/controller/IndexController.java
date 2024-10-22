package br.edu.fesa.aquela_loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping({"/", "/{page}"})
    public String loadPage(@PathVariable(required = false) String page) {
        // Se a variável 'page' for nula, é a URL raiz ("/"), então carregue a página home
        if (page == null || page.isEmpty()) {
            return "pages/home :: content"; // retorna o arquivo home.html
        }
    
        return "pages/"+ page+" :: content";
    }

}
