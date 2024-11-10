package br.edu.fesa.aquela_loja.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    //Admin Permissions
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    //Admin Permissions
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:write"),
    USER_DELETE("user:delete");

    @Getter
    private final String permission;
}
