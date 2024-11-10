package br.edu.fesa.aquela_loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
import br.edu.fesa.aquela_loja.models.dto.UpdateUserDto;
import br.edu.fesa.aquela_loja.models.dto.UserDto;
import br.edu.fesa.aquela_loja.service.IUserService;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/user/registration")
    public String registration(NewUserDto newUserDto) {
        userService.createNewUser(newUserDto);

        return "redirect:/";
    }

    @GetMapping(value = "/usuario")
    public String getUserName(Model model) {

        UserDto user = userService.getAuthenticatedUser();

        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userEmail", user.getEmail());

        return "pages/user_pages/usuario";
    }

    @PutMapping("/user/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody UpdateUserDto entity) {

        userService.updateUser(id, entity);

        return "pages/user_pages/meus-dados";
    }

    @GetMapping(value = "/usuario/meus-dados")
    public String editUserDetails(Model model) {
        return "pages/user_pages/meus-dados";
    }

    @GetMapping("/usuario/meus-pedidos")
    public String getUserOrders(Model model) {
        return "pages/user_pages/meus-pedidos";
    }

    @GetMapping("/usuario/meus-cartoes")
    public String getUserCard(Model model) {
        return "pages/user_pages/meus-cartoes";
    }
}
