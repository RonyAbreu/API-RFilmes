package com.ronyelison.filmes.services;

import com.ronyelison.filmes.dto.usuario.TokenDTO;
import com.ronyelison.filmes.dto.usuario.UsuarioDeLogin;
import com.ronyelison.filmes.dto.usuario.UsuarioDeRegistroDTO;
import com.ronyelison.filmes.dto.usuario.UsuarioDeRespostaDTO;
import com.ronyelison.filmes.jwt.TokenService;
import com.ronyelison.filmes.mapper.Mapeador;
import com.ronyelison.filmes.models.Usuario;
import com.ronyelison.filmes.models.enums.CargoUsuario;
import com.ronyelison.filmes.repositories.UsuarioRepository;
import com.ronyelison.filmes.services.exceptions.UsuarioJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository repository;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UsuarioService(UsuarioRepository repository, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public UsuarioDeRespostaDTO cadastrarUsuario(UsuarioDeRegistroDTO usuarioDeRegistroDTO){
        Optional<Usuario> usuarioRetornado = repository.findByEmail(usuarioDeRegistroDTO.getEmail());

        if (usuarioRetornado.isPresent()){
            throw new UsuarioJaExisteException("Tente utilizar outro email!");
        }

        Usuario usuarioParaSalvar = new Usuario(usuarioDeRegistroDTO);
        criptografarSenha(usuarioParaSalvar);
        repository.save(usuarioParaSalvar);

        return Mapeador.converterObjeto(usuarioParaSalvar, UsuarioDeRespostaDTO.class);
    }

    private void criptografarSenha(Usuario usuario){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
    }

    public TokenDTO logarUsuario(UsuarioDeLogin usuarioDeLogin){
        var usuarioLoginSenha = new UsernamePasswordAuthenticationToken(usuarioDeLogin.getEmail(),usuarioDeLogin.getSenha());
        var usuarioAutenticado = authenticationManager.authenticate(usuarioLoginSenha);
        String token = tokenService.gerarToken((Usuario)usuarioAutenticado.getPrincipal());
        return new TokenDTO(token);
    }

}
