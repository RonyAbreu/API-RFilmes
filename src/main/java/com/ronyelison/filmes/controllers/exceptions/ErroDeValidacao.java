package com.ronyelison.filmes.controllers.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class ErroDeValidacao extends RespostaDeErro{

    private List<TituloMensagemErro> erros = new ArrayList<>();

    public ErroDeValidacao(Instant timestamp, Integer status, String mensagem, String caminho) {
        super(timestamp, status, mensagem, caminho);
    }

    public void adicionaErros(String titulo, String mensagem){
        erros.add(new TituloMensagemErro(titulo,mensagem));
    }

}
