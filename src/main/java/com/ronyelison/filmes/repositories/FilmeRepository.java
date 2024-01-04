package com.ronyelison.filmes.repositories;

import com.ronyelison.filmes.models.Filme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme,Long> {
    Optional<Filme> findByTituloAndTempoEmMinutos(String titulo, Integer tempoEmMinutos);
    Page<Filme> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
