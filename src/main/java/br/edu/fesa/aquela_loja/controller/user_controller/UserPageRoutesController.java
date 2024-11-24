package br.edu.fesa.aquela_loja.controller.user_controller;

import java.util.List;

import br.edu.fesa.aquela_loja.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.fesa.aquela_loja.models.dto.UserDto;
import br.edu.fesa.aquela_loja.models.dto.address.NewUserAddressDto;
import br.edu.fesa.aquela_loja.models.dto.address.UserAddressDto;
import br.edu.fesa.aquela_loja.models.dto.card.NewUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.card.UserCardDto;
import br.edu.fesa.aquela_loja.service.user_services.UserAddressService;
import br.edu.fesa.aquela_loja.service.user_services.UserCardService;
import br.edu.fesa.aquela_loja.service.user_services.UserService;

@Controller
@RequestMapping("/usuario")
public class UserPageRoutesController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserCardService userCardService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAddressService userAddressService;

    
    @GetMapping({"/", ""})
    public String userPage(Model model) {

        UserDto user = userService.getAuthenticatedUser();

        model.addAttribute("userName", user.getUserName());
        model.addAttribute("userEmail", user.getEmail());

        return "pages/user_pages/usuario";
    }

    @GetMapping(value = "/meus-dados")
    public String editUserDetailsPage(Model model) {

        UserDto user = userService.getAuthenticatedUser();
        List<UserAddressDto> userAddress = userAddressService.getUserAddress();

        model.addAttribute("updatedUser", user);
        model.addAttribute("userAddress", userAddress);
        model.addAttribute("newUserAddress", new NewUserAddressDto());

        return "pages/user_pages/meus-dados";
    }

    @GetMapping("/meus-pedidos")
    public String userOrdersPage(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth.getAuthorities().stream().toList().get(0).getAuthority().equals("ROLE_ADMIN")) {
            model.addAttribute("order", orderService.findAll());
        } else {
            model.addAttribute("order", orderService.findOrderByClient(userService.findUserByEmail(auth.getName())));
        }

        return "pages/user_pages/meus-pedidos";
    }

    @GetMapping("/meus-cartoes")
    public String userCardPage(Model model) {
        List<UserCardDto> cards = userCardService.getUserCards();

        model.addAttribute("userCards", cards);

        return "pages/user_pages/meus-cartoes";
    }

    @GetMapping("/meus-cartoes/novo")
    public String newUserCardPage(Model model) {

        model.addAttribute("newCard", new NewUserCardDto());
        return "pages/user_pages/novo-cartao";
    }
}
