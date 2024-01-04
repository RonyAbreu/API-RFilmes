package com.ronyelison.filmes.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDeLogin {
    @NotBlank(message = "Campo 'EMAIL' não pode ser vazio")
    @Size(max = 50, message = "Número de caracteres inválido")
    @Email(message = "Verifique se digitou o email corretamente")
    private String email;

    @NotBlank(message = "Campo 'SENHA' não pode ser vazio")
    @Size(min = 8, max = 26, message = "Número de caracteres inválido")
    private String senha;
}
