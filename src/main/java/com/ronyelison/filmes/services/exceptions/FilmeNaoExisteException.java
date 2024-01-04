package com.ronyelison.filmes.services.exceptions;

public class FilmeNaoExisteException extends RuntimeException{
    public FilmeNaoExisteException(String mensagem){
        super(mensagem);
    }
}
