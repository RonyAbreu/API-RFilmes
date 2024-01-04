package com.ronyelison.filmes.services.exceptions;

public class FilmeJaExisteException extends RuntimeException{
    public FilmeJaExisteException(String mensagem){
        super(mensagem);
    }
}
