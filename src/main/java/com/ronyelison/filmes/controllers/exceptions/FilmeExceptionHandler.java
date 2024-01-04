package com.ronyelison.filmes.controllers.exceptions;

import com.ronyelison.filmes.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class FilmeExceptionHandler{

    @ExceptionHandler(FilmeJaExisteException.class)
    public ResponseEntity<RespostaDeErro> filmeJaExisteException(FilmeJaExisteException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        RespostaDeErro respostaDeErro = new RespostaDeErro(Instant.now(),status.value(),exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(respostaDeErro);
    }

    @ExceptionHandler(FilmeNaoExisteException.class)
    public ResponseEntity<RespostaDeErro> filmeNaoExisteException(FilmeNaoExisteException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        RespostaDeErro respostaDeErro = new RespostaDeErro(Instant.now(),status.value(),exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(respostaDeErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaDeErro> erroDeValidacaoException(MethodArgumentNotValidException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroDeValidacao respostaDeErro = new ErroDeValidacao(Instant.now(),status.value(),"Erro de validação!",request.getRequestURI());

        for (FieldError error : exception.getBindingResult().getFieldErrors()){
            respostaDeErro.adicionaErros(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(respostaDeErro);
    }

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<RespostaDeErro> usuarioJaExisteException(UsuarioJaExisteException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        RespostaDeErro respostaDeErro = new RespostaDeErro(Instant.now(),status.value(),exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(respostaDeErro);
    }

    @ExceptionHandler(UsuarioNaoExisteException.class)
    public ResponseEntity<RespostaDeErro> usuarioNaoExisteException(UsuarioNaoExisteException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        RespostaDeErro respostaDeErro = new RespostaDeErro(Instant.now(),status.value(),exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(respostaDeErro);
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<RespostaDeErro> tokenInvalidoException(TokenInvalidoException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        RespostaDeErro respostaDeErro = new RespostaDeErro(Instant.now(),status.value(),exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(respostaDeErro);
    }
}
