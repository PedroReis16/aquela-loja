package br.edu.fesa.aquela_loja.service.user_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
import br.edu.fesa.aquela_loja.models.dto.UserDto;
import br.edu.fesa.aquela_loja.models.entity.UserModel;
import br.edu.fesa.aquela_loja.repository.IUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final IUserRepository appUserRepository;

    @Autowired
    private final UserAddressService userAddressService;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;


    public void createNewUser(NewUserDto newUser) {
        UserModel appUser = UserModel.builder()
                .username(newUser.getUsername())
                .document(newUser.getDocument())
                .gender(newUser.getGender())
                .birthdate(newUser.getBirthdate())
                .phone(newUser.getPhone())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(newUser.getRole())
                .build();

        // AddressModel addressModel = AddressModel.builder()
        //         .cep(newUser.getCep())
        //         .addressIdentification(newUser.getAddressIdentification())
        //         .street(newUser.getStreet())
        //         .number(newUser.getNumber())
        //         .neighborhood(newUser.getNeighborhood())
        //         .city(newUser.getCity())
        //         .state(newUser.getState())
        //         .complement(newUser.getComplement())
        //         .reference(newUser.getReference())
        //         .isDefault(true)
        //         .appUser(appUser)
        //         .build();

        appUserRepository.save(appUser);
        userAddressService.newAddressFromEntity(newUser.toNewUserAddressDto(),appUser);
    }

    public UserDto getAuthenticatedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserModel appUser = appUserRepository.findByEmail(auth.getName()).get();
        // AppUserModel appUser = appUserRepository.findByDocument(auth.getCredentials().)

        UserDto user = new UserDto(appUser);

        return user;
    }

    public void updateUser(UserDto updatedUser) {
        UserModel savedUser = appUserRepository.findByDocument(updatedUser.getDocument()).get();

        savedUser.setUsername(updatedUser.getUserName());
        savedUser.setEmail(updatedUser.getEmail());
        savedUser.setGender(updatedUser.getGender());
        savedUser.setPhone(updatedUser.getPhone());

        appUserRepository.save(savedUser);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        appUserRepository.delete(appUser);

        // Invalida a sessão
        request.getSession().invalidate();

        // Remove todos os cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {

        }
    }

    public UserDto FindUserByDocument(String document) {
        try {
            UserModel appUser = appUserRepository.findByDocument(document).get();

            UserDto user = new UserDto(appUser);

            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public UserDto FindUserByEmail(String email) {
        try {
            UserModel appUser = appUserRepository.findByEmail(email).get();

            UserDto user = new UserDto(appUser);

            return user;
        } catch (Exception e) {
            return null;
        }
    }

    // private String formatUserDocument(String document) {
    //     if (document == null || document.length() != 11) {
    //         throw new IllegalArgumentException("Documento inválido");
    //     }
    //     return document.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    // }

    // private String formatUserPhone(String phone) {
    //     if (phone == null || phone.length() != 11) {
    //         throw new IllegalArgumentException("Número de telefone inválido");
    //     }
    //     return phone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    // }

    public UserModel findUserByEmail(String email) {
        return appUserRepository.findByEmail(email).orElseThrow();
    }

}
