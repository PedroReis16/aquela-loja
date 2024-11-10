package br.edu.fesa.aquela_loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
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

        model.addAttribute("userName", user.getUserName());
        model.addAttribute("userEmail", user.getEmail());

        return "pages/user_pages/usuario";
    }

    @GetMapping(value = "/usuario/meus-dados")
    public String editUserDetails(Model model) {

        UserDto user = userService.getAuthenticatedUser();

        // model.addAttribute("user", user);
        model.addAttribute("updatedUser", user);
        return "pages/user_pages/meus-dados";
    }

    @PostMapping(value = "/user/edit")
    public String putMethodName(@ModelAttribute UserDto updatedUser) {

        userService.updateUser( updatedUser);

        return "redirect:/pages/user_pages/meus-dados";
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
