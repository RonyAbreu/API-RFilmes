package com.ronyelison.filmes.services;

import com.ronyelison.filmes.dto.filme.FilmeRegistroDTO;
import com.ronyelison.filmes.mock.FilmeMock;
import com.ronyelison.filmes.models.Filme;
import com.ronyelison.filmes.repositories.FilmeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {
    FilmeMock mock;
    @InjectMocks
    FilmeService service;
    @Mock
    FilmeRepository repository;

    @BeforeEach
    void setup(){
        mock = new FilmeMock();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarFilme() {
        Filme filme = mock.mockFilme(1);
        FilmeRegistroDTO filmeRegistroDTO = mock.mockFilmeDTO(1);

        Mockito.lenient().when(repository.save(filme)).thenReturn(filme);

        var resultado = service.cadastrarFilme(filmeRegistroDTO);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertNotNull(resultado.getLinks());

        assertEquals("Filme 1", resultado.getTitulo());
        assertEquals(60, resultado.getTempoEmMinutos());
        assertTrue(resultado.toString().contains("links: [</api/v1/filmes/1>;rel=\"self\"]"));
    }

    @Test
    void removerFilme() {
        Filme filme = mock.mockFilme(1);

        Mockito.lenient().when(repository.findById(filme.getId())).thenReturn(Optional.of(filme));

        service.removerFilme(filme.getId());
    }

    @Test
    void retornaFilme() {
        Filme filme = mock.mockFilme(1);

        Mockito.lenient().when(repository.findById(filme.getId())).thenReturn(Optional.of(filme));

        var resultado = service.retornaFilme(filme.getId());

        assertNotNull(resultado);
        assertNotNull(resultado.getLinks());

        assertEquals("Filme 1", resultado.getTitulo());
        assertEquals(60, resultado.getTempoEmMinutos());
        assertTrue(resultado.toString().contains("links: [</api/v1/filmes/1>;rel=\"self\"]"));
    }


    @Test
    void atualizaFilme() {
        Filme filme = mock.mockFilme(1);

        FilmeRegistroDTO filmeRegistroDTO = mock.mockFilmeDTO(5);

        Mockito.lenient().when(repository.findById(filme.getId())).thenReturn(Optional.of(filme));
        Mockito.lenient().when(repository.save(filme)).thenReturn(filme);

        var resultado = service.atualizaFilme(filmeRegistroDTO, filme.getId());

        assertNotNull(resultado);
        assertNotNull(resultado.getLinks());

        assertEquals("Filme 5", resultado.getTitulo());
        assertEquals("Descrição 5", resultado.getDescricao());
        assertTrue(resultado.toString().contains("links: [</api/v1/filmes/1>;rel=\"self\"]"));
    }
}