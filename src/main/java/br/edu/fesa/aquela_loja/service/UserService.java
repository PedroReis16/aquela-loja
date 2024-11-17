package br.edu.fesa.aquela_loja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.models.dto.NewUserAddressDto;
import br.edu.fesa.aquela_loja.models.dto.NewUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
import br.edu.fesa.aquela_loja.models.dto.UpdateUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.UserAddressDto;
import br.edu.fesa.aquela_loja.models.dto.UserCardDto;
import br.edu.fesa.aquela_loja.models.dto.UserDto;
import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.models.entity.PaymentCardModel;
import br.edu.fesa.aquela_loja.repository.IAddressRepository;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;
import br.edu.fesa.aquela_loja.repository.IPaymentCardRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final IAppUserRepository appUserRepository;

    @Autowired
    private final IAddressRepository addressRepository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private final IPaymentCardRepository paymentCardRepository;

    public void createNewUser(NewUserDto newUser) {
        AppUserModel appUser = AppUserModel.builder()
                .username(newUser.getUsername())
                .document(newUser.getDocument())
                .gender(newUser.getGender())
                .birthdate(newUser.getBirthdate())
                .phone(newUser.getPhone())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(newUser.getRole())
                .build();

        AddressModel addressModel = AddressModel.builder()
                .cep(newUser.getCep())
                .addressIdentification(newUser.getAddressIdentification())
                .street(newUser.getStreet())
                .number(newUser.getNumber())
                .neighborhood(newUser.getNeighborhood())
                .city(newUser.getCity())
                .state(newUser.getState())
                .complement(newUser.getComplement())
                .reference(newUser.getReference())
                .isDefault(true)
                .appUser(appUser)
                .build();

        appUserRepository.save(appUser);
        addressRepository.save(addressModel);
    }

    public UserDto getAuthenticatedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = appUserRepository.findByEmail(auth.getName()).get();
        // AppUserModel appUser = appUserRepository.findByDocument(auth.getCredentials().)

        UserDto user = new UserDto(appUser);

        return user;
    }

    public void updateUser(UserDto updatedUser) {
        AppUserModel savedUser = appUserRepository.findByDocument(updatedUser.getDocument()).get();

        savedUser.setUsername(updatedUser.getUserName());
        savedUser.setEmail(updatedUser.getEmail());
        savedUser.setGender(updatedUser.getGender());
        savedUser.setPhone(updatedUser.getPhone());

        appUserRepository.save(savedUser);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

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

    public List<UserAddressDto> getUserAddress() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        List<AddressModel> userAddresses = addressRepository.findByAppUser(appUser).get();
        List<UserAddressDto> result = new ArrayList<>();

        for (AddressModel address : userAddresses) {
            UserAddressDto userAddress = new UserAddressDto(address);
            result.add(userAddress);
        }

        return result;
    }

    public void addNewAddress(NewUserAddressDto newUserAddress) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        AddressModel addressModel = AddressModel.builder()
                .cep(newUserAddress.getCep())
                .addressIdentification(newUserAddress.getAddressIdentification())
                .street(newUserAddress.getStreet())
                .number(newUserAddress.getNumber())
                .neighborhood(newUserAddress.getNeighborhood())
                .city(newUserAddress.getCity())
                .state(newUserAddress.getState())
                .complement(newUserAddress.getComplement())
                .reference(newUserAddress.getReference())
                .isDefault(false)
                .appUser(appUser)
                .build();

        addressRepository.save(addressModel);
    }

    public void updateUserAddress(UserAddressDto userAddressDto) {
        AddressModel addressModel = addressRepository.findById(userAddressDto.getId()).get();

        addressModel.setCep(userAddressDto.getCep());
        addressModel.setAddressIdentification(userAddressDto.getAddressIdentification());
        addressModel.setStreet(userAddressDto.getStreet());
        addressModel.setNumber(userAddressDto.getNumber());
        addressModel.setNeighborhood(userAddressDto.getNeighborhood());
        addressModel.setCity(userAddressDto.getCity());
        addressModel.setState(userAddressDto.getState());
        addressModel.setComplement(userAddressDto.getComplement());
        addressModel.setReference(userAddressDto.getReference());
        addressModel.setIsDefault(userAddressDto.isDefault());

        addressRepository.save(addressModel);
    }

    public void deleteUserAddress(Long id) {
        //TODO: Colocar uma validação que não permita deletar o endereço caso ele seja o único disponível
        addressRepository.deleteById(id);
    }

    public List<UserCardDto> getUserCard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        List<PaymentCardModel> userCards = paymentCardRepository.findByAppUser(appUser).get();

        List<UserCardDto> result = new ArrayList<>();

        for (PaymentCardModel card : userCards) {
            UserCardDto userCard = new UserCardDto(card);
            result.add(userCard);
        }

        return result;
    }

    public void addNewCard(NewUserCardDto newCard) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        PaymentCardModel card = PaymentCardModel.builder()
                .holderName(newCard.getHolderName())
                .number(newCard.getNumber())
                .expirationDate(newCard.getExpirationDate())
                .cvv(newCard.getCvv())
                .appUser(appUser)
                .build();

        paymentCardRepository.save(card);
    }

    public void deleteUserCard(Long id) {
        paymentCardRepository.deleteById(id);
    }

    public void updateUserCard(Long id, UpdateUserCardDto updatedCard) {
        PaymentCardModel card = paymentCardRepository.findById(id).get();

        card.setHolderName(updatedCard.getHolderName());
        card.setNumber(updatedCard.getExpirationDate());

        paymentCardRepository.save(card);
    }

    public AppUserModel findUserByEmail(String email) {
        return appUserRepository.findByEmail(email).orElseThrow();
    }

}
