package com.ronyelison.filmes.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TituloMensagemErro {
    private String titulo;
    private String mensagem;
}
