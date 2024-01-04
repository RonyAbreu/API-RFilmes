package com.ronyelison.filmes.dto.filme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmeRegistroDTO {
    @NotBlank(message = "O campo 'TITULO' não pode ser vazio")
    @Size(max = 50, message = "número de caracteres inválidos")
    private String titulo;

    @NotBlank(message = "O campo 'DESCRIÇÃO' não pode ser vazio")
    @Size(max = 350, message = "número de caracteres inválidos")
    private String descricao;

    @NotBlank(message = "O campo 'URL' não pode ser vazio")
    @Size(max = 250, message = "número de caracteres inválidos")
    private String urlDaCapa;

    @NotBlank(message = "O campo 'CATEGORIA' não pode ser vazio")
    @Size(min = 4, max = 40, message = "número de caracteres inválidos")
    private String categoria;

    @NotBlank(message = "O campo 'DIRETOR' não pode ser vazio")
    @Size(min = 3,max = 60, message = "número de caracteres inválidos")
    private String diretor;

    @NotNull(message = "O campo 'ANO' não pode ser vazio")
    private Integer anoDeLancamento;

    @NotNull(message = "O campo 'TEMPO' não pode ser vazio")
    private Integer tempoEmMinutos;
}
