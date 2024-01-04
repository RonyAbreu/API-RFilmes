package com.ronyelison.filmes.dto.filme;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmeRespostaDTO extends RepresentationModel<FilmeRespostaDTO>{
    private Long id;
    private String titulo;
    private String descricao;
    private String urlDaCapa;
    private String categoria;
    private String diretor;
    private Integer anoDeLancamento;
    private Integer tempoEmMinutos;
}
