package com.ronyelison.filmes.dto.filme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmeRespostaMinimoDTO extends RepresentationModel<FilmeRespostaMinimoDTO>{
    private Long id;
    private String titulo;
    private String urlDaCapa;
}

