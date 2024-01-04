package com.ronyelison.filmes.mock;

import com.ronyelison.filmes.dto.filme.FilmeRegistroDTO;
import com.ronyelison.filmes.models.Filme;

import java.util.ArrayList;
import java.util.List;

public class FilmeMock {
    public Filme mockFilme(Integer numero){
        Filme filme = new Filme();
        filme.setId(Long.valueOf(numero));
        filme.setTitulo("Filme " + numero);
        filme.setDescricao("Descrição " + numero);
        filme.setUrlDaCapa("filme.com.br");
        filme.setAnoDeLancamento(2023);
        filme.setTempoEmMinutos(60);
        return filme;
    }

    public List<Filme> mockListaDeFilmes(){
        List<Filme> listaDeFilmes = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            listaDeFilmes.add(mockFilme(i));
        }
        return listaDeFilmes;
    }

    public FilmeRegistroDTO mockFilmeDTO(Integer numero){
        FilmeRegistroDTO filmeRegistroDTO = new FilmeRegistroDTO();
        filmeRegistroDTO.setTitulo("Filme " + numero);
        filmeRegistroDTO.setDescricao("Descrição " + numero);
        filmeRegistroDTO.setUrlDaCapa("filmeRegistroDTO.com.br");
        filmeRegistroDTO.setAnoDeLancamento(2023);
        filmeRegistroDTO.setTempoEmMinutos(60);
        return filmeRegistroDTO;
    }
}
