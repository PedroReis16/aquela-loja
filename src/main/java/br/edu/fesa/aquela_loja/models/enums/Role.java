package br.edu.fesa.aquela_loja.models.enums;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static br.edu.fesa.aquela_loja.models.enums.Permission.ADMIN_CREATE;
import static br.edu.fesa.aquela_loja.models.enums.Permission.ADMIN_DELETE;
import static br.edu.fesa.aquela_loja.models.enums.Permission.ADMIN_READ;
import static br.edu.fesa.aquela_loja.models.enums.Permission.ADMIN_UPDATE;
import static br.edu.fesa.aquela_loja.models.enums.Permission.USER_CREATE;
import static br.edu.fesa.aquela_loja.models.enums.Permission.USER_DELETE;
import static br.edu.fesa.aquela_loja.models.enums.Permission.USER_READ;
import static br.edu.fesa.aquela_loja.models.enums.Permission.USER_UPDATE;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

    //Usuário somente tem a permissão de usuário
    USER(Set.of(USER_READ, USER_CREATE, USER_UPDATE, USER_DELETE)),
    //Admin permissão de usuário e administrador
    ADMIN(Set.of(USER_READ, USER_CREATE, USER_UPDATE, USER_DELETE, ADMIN_READ, ADMIN_CREATE, ADMIN_UPDATE, ADMIN_DELETE));

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        authorities.add(new SimpleGrantedAuthority(this.name()));
        return authorities;
    }
}
