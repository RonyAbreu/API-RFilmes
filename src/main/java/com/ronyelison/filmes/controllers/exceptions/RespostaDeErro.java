package com.ronyelison.filmes.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespostaDeErro {
    private Instant timestamp;
    private Integer status;
    private String mensagem;
    private String caminho;
}
