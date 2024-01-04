package com.ronyelison.filmes.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Filme")
@Table(name = "tb_filmes")
public class Filme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(length = 500)
    private String descricao;
    private String urlDaCapa;
    private String categoria;
    private String diretor;
    private Integer anoDeLancamento;
    private Integer tempoEmMinutos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(titulo, filme.titulo) && Objects.equals(anoDeLancamento, filme.anoDeLancamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, anoDeLancamento);
    }
}
