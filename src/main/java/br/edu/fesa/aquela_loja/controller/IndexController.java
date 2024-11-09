package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.controller.dto.RegistrationDto;
import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.repository.IAddressRepository;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class IndexController {

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @GetMapping({"/", "/{page}"})
//    public String loadPage(Model model, @PathVariable(required = false) String page) {
//
//        if (page == null || page.isEmpty() || !page.isEmpty() && "inicio".equals(page)) {
//            model.addAttribute("contentFragment", "pages/inicio");
//        } else {
//            model.addAttribute("contentFragment", "pages/" + page);
//        }
//
//        return "index";
//
//    }
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(RegistrationDto registrationDto) {
        return "cadastrar";
    }

    @PostMapping(value = "/user/registration")
    public String registration(RegistrationDto registrationDto) {

        AppUserModel appUser = AppUserModel.builder()
                .username(registrationDto.getUsername())
                .document(registrationDto.getDocument())
                .gender(registrationDto.getGender())
                .birthdate(registrationDto.getBirthdate())
                .phone(registrationDto.getPhone())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .build();

        AddressModel addressModel = AddressModel.builder()
                .cep(registrationDto.getCep())
                .addressIdentification(registrationDto.getAddressIdentification())
                .street(registrationDto.getStreet())
                .number(registrationDto.getNumber())
                .neighborhood(registrationDto.getNeighborhood())
                .city(registrationDto.getCity())
                .state(registrationDto.getState())
                .complement(registrationDto.getComplement())
                .reference(registrationDto.getReference())
                .appUser(appUser)
                .build();

        appUserRepository.save(appUser);
        addressRepository.save(addressModel);

        return "redirect:/";
    }

    @GetMapping("/seguro/home")
    public String homesegura() {
        return "homeSegura";
    }

    @GetMapping({"/usuario/{page}"})
    public String loadUserPages(Model model, @PathVariable String page) {
        model.addAttribute("contentFragment", "pages/user_pages/" + page);
        return "index";
    }

}
