package com.ronyelison.filmes.services;

import com.ronyelison.filmes.dto.usuario.UsuarioDeRespostaDTO;
import com.ronyelison.filmes.mapper.Mapeador;
import com.ronyelison.filmes.models.Usuario;
import com.ronyelison.filmes.repositories.UsuarioRepository;
import com.ronyelison.filmes.services.exceptions.UsuarioNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioAutenticadoService {
    private UsuarioRepository repository;

    @Autowired
    public UsuarioAutenticadoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void removerUsuario(Long id){
        Optional<Usuario> usuarioParaRemover = repository.findById(id);

        if (usuarioParaRemover.isEmpty()){
            throw new UsuarioNaoExisteException("Usuário não encontrado!");
        }

        repository.deleteById(id);
    }

    public UsuarioDeRespostaDTO retornarUsuario(Long id){
        Optional<Usuario> usuarioRetornado = repository.findById(id);

        if (usuarioRetornado.isEmpty()){
            throw new UsuarioNaoExisteException("Usuário não encontrado!");
        }

        return Mapeador.converterObjeto(usuarioRetornado, UsuarioDeRespostaDTO.class);
    }
}
