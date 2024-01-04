package com.ronyelison.filmes.models.enums;

public enum CargoUsuario {
    ADMIN("admin"),
    USER("user");
    private String role;

    CargoUsuario(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
