package br.edu.fesa.aquela_loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.fesa.aquela_loja.models.dto.NewUserAddressDto;
import br.edu.fesa.aquela_loja.models.dto.NewUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
import br.edu.fesa.aquela_loja.models.dto.UpdateUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.UserAddressDto;
import br.edu.fesa.aquela_loja.models.dto.UserCardDto;
import br.edu.fesa.aquela_loja.models.dto.UserDto;
import br.edu.fesa.aquela_loja.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/registration")
    public String registration(HttpServletRequest request, NewUserDto newUserDto) {
        userService.createNewUser(newUserDto);
        userService.authWithHttpServletRequest(request, newUserDto.getEmail(), newUserDto.getPassword());
        //  String username, String password
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
        List<UserAddressDto> userAddress = userService.getUserAddress();

        model.addAttribute("updatedUser", user);
        model.addAttribute("userAddress", userAddress);
        model.addAttribute("newUserAddress", new NewUserAddressDto());

        return "pages/user_pages/meus-dados";
    }

    @PostMapping(value = "/user/edit")
    public String putMethodName(@ModelAttribute UserDto updatedUser) {

        userService.updateUser(updatedUser);

        return "redirect:/usuario/meus-dados";
    }

    @GetMapping(value = "user/delete")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        userService.deleteUser(request, response);

        return "redirect:/";
    }

    @PostMapping("/user/new-address")
    public String newUserAddress(@ModelAttribute("newUserAddress") NewUserAddressDto newUserAddress) {
        userService.addNewAddress(newUserAddress);

        return "redirect:/usuario/meus-dados";
    }

    @PostMapping("/user/update-address")
    public ResponseEntity<Void> updateUserAddress(@RequestBody UserAddressDto updatedAddress) {
        userService.updateUserAddress(updatedAddress);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/update-address/{id}")
    public ResponseEntity<Void> setDefaultAddress(@PathVariable Long id) {

        userService.setDefaultAddress(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/delete-address/{id}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable Long id) {
        userService.deleteUserAddress(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/meus-pedidos")
    public String getUserOrders(Model model) {
        return "pages/user_pages/meus-pedidos";
    }

    @GetMapping("/usuario/meus-cartoes")
    public String getUserCard(Model model) {
        List<UserCardDto> cards = userService.getUserCard();

        model.addAttribute("userCards", cards);

        return "pages/user_pages/meus-cartoes";
    }

    @GetMapping("/usuario/meus-cartoes/novo")
    public String getMethodName(Model model) {

        model.addAttribute("newCard", new NewUserCardDto());
        return "pages/user_pages/novo-cartao";
    }

    @PostMapping("/user/new-card")
    public String newUserCard(@ModelAttribute("newUserAddress") NewUserCardDto newCard) {
        userService.addNewCard(newCard);

        return "redirect:/usuario/meus-cartoes";
    }

    @PostMapping("user/update-card/{id}")
    public String updateUserCard(@PathVariable Long id, @RequestBody UpdateUserCardDto updatedCard) {
        userService.updateUserCard(id, updatedCard);

        return "redirect:/usuario/meus-cartoes";
    }

    @PostMapping("user/delete-card/{id}")
    public String deleteUserCard(@PathVariable Long id) {

        userService.deleteUserCard(id);

        return "redirect:/usuario/meus-cartoes";
    }

    @GetMapping("user/documents")
    public ResponseEntity<Void> isDocumentAvailable(@RequestParam String document) {
        UserDto user = userService.FindUserByDocument(document);

        if (user != null) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping("user/emails")
    public ResponseEntity<Void> isEmailAvailable(@RequestParam String email) {
        UserDto user = userService.FindUserByEmail(email);

        if (user != null) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }

}
