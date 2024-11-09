package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.fesa.aquela_loja.models.enums.Role;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String document;

    private String gender;

    private String birthdate;

    private String phone;

    private String email;

    private String password;

    private Role role;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<AddressModel> address;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<PaymentCardModel> wallet;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
}
