package com.ronyelison.filmes.services.exceptions;

public class TokenInvalidoException extends RuntimeException{
    public TokenInvalidoException(String mensagem){
        super(mensagem);
    }
}
