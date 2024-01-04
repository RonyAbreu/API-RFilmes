package com.ronyelison.filmes.services.exceptions;

public class UsuarioNaoExisteException extends RuntimeException{
    public UsuarioNaoExisteException(String msg){
        super(msg);
    }
}
