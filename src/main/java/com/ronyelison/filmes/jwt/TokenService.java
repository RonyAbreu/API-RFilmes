package com.ronyelison.filmes.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ronyelison.filmes.models.Usuario;
import com.ronyelison.filmes.services.exceptions.TokenInvalidoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${security.key:secret}")
    private String secret;
    private final String API_NOME = "API Filmes";

    public String gerarToken(Usuario usuarioDeLogin){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(API_NOME)
                    .withSubject(usuarioDeLogin.getEmail())
                    .withExpiresAt(tempoDoToken())
                    .sign(algorithm);
        }catch (Exception e){
            throw new TokenInvalidoException("Token inválido!");
        }
    }

    private Instant tempoDoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validaUsuarioPeloToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(API_NOME)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e){
            throw new TokenInvalidoException("Token inválido!");
        }
    }
}
