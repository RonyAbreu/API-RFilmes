package com.ronyelison.filmes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ronyelison.filmes.dto.usuario.UsuarioDeRegistroDTO;
import com.ronyelison.filmes.models.enums.CargoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Usuario")
@Table(name = "tb_usuarios")
public class Usuario implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    @Enumerated(value = EnumType.STRING)
    private CargoUsuario role = CargoUsuario.USER;
    private Boolean contaNaoExpirada = true;
    private Boolean contaNaoBloqueada = true;
    private Boolean credenciaisNaoBloqueadas = true;
    private Boolean ativa = true;

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.contaNaoExpirada = true;
        this.contaNaoBloqueada = true;
        this.credenciaisNaoBloqueadas = true;
        this.ativa = true;
    }

    public Usuario(UsuarioDeRegistroDTO usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == CargoUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return senha;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return contaNaoExpirada;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return contaNaoBloqueada;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return credenciaisNaoBloqueadas;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return ativa;
    }
}
