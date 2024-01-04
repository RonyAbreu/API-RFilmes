package com.ronyelison.filmes.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDeRegistroDTO {
    @NotBlank(message = "Campo 'NOME' não pode ser vazio")
    @Size(min = 4, max = 60, message = "Número de caracteres inválido")
    private String nome;

    @NotBlank(message = "Campo 'EMAIL' não pode ser vazio")
    @Size(max = 50, message = "Número de caracteres inválido")
    @Email(message = "Verifique se digitou o email corretamente")
    private String email;

    @NotBlank(message = "Campo 'SENHA' não pode ser vazio")
    @Size(min = 8, max = 20, message = "Número de caracteres inválido")
    private String senha;
}
