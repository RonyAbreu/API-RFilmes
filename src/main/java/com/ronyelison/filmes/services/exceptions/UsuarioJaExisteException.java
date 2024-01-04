package com.ronyelison.filmes.services.exceptions;

public class UsuarioJaExisteException extends RuntimeException{
    public UsuarioJaExisteException(String msg){
        super(msg);
    }
}
