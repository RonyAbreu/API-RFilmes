package com.ronyelison.filmes.services;

import com.ronyelison.filmes.controllers.FilmeController;
import com.ronyelison.filmes.dto.filme.FilmeRegistroDTO;
import com.ronyelison.filmes.dto.filme.FilmeRespostaDTO;
import com.ronyelison.filmes.dto.filme.FilmeRespostaMinimoDTO;
import com.ronyelison.filmes.mapper.Mapeador;
import com.ronyelison.filmes.models.Filme;
import com.ronyelison.filmes.repositories.FilmeRepository;
import com.ronyelison.filmes.services.exceptions.FilmeJaExisteException;
import com.ronyelison.filmes.services.exceptions.FilmeNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FilmeService {
    private FilmeRepository repository;
    private PagedResourcesAssembler<FilmeRespostaMinimoDTO> assembler;

    @Autowired
    public FilmeService(FilmeRepository repository, PagedResourcesAssembler<FilmeRespostaMinimoDTO> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public FilmeRespostaDTO cadastrarFilme(FilmeRegistroDTO filmeRegistroDTO){
        Optional<Filme> filmeRetornado = repository.findByTituloAndTempoEmMinutos(filmeRegistroDTO.getTitulo(),filmeRegistroDTO.getTempoEmMinutos());

        if (filmeRetornado.isPresent()){
            throw new FilmeJaExisteException("Filme já foi cadastrado!");
        }

        Filme filmeParaSalvar = Mapeador.converterObjeto(filmeRegistroDTO, Filme.class);

        FilmeRespostaDTO filmeDeResposta = Mapeador.converterObjeto(repository.save(filmeParaSalvar),FilmeRespostaDTO.class);
        adicionaLinks(filmeDeResposta);

        return filmeDeResposta;
    }

    private void adicionaLinks(FilmeRespostaDTO filme){
        filme.add(
                linkTo(
                        methodOn(FilmeController.class)
                                .retornaFilme(filme.getId()))
                                .withSelfRel());
    }

    private void adicionaLinks(FilmeRespostaMinimoDTO filme){
        filme.add(
                linkTo(
                        methodOn(FilmeController.class)
                                .retornaFilme(filme.getId()))
                        .withSelfRel());
    }


    public void removerFilme(Long id){
        Optional<Filme> filmeParaRemover = repository.findById(id);

        if (filmeParaRemover.isEmpty()){
            throw new FilmeNaoExisteException("Filme não encontrado!");
        }

        repository.deleteById(id);
    }

    public FilmeRespostaDTO retornaFilme(Long id){
        Optional<Filme> filmeRetornado = repository.findById(id);

        if (filmeRetornado.isEmpty()){
            throw new FilmeNaoExisteException("Filme não encontrado!");
        }

        FilmeRespostaDTO filmeDeResposta = Mapeador.converterObjeto(filmeRetornado.get(), FilmeRespostaDTO.class);
        adicionaLinks(filmeDeResposta);

        return filmeDeResposta;
    }

    public PagedModel<EntityModel<FilmeRespostaMinimoDTO>> retornarTodosFilmes(Pageable pageable){
        Page<Filme> filmesRetornados = repository.findAll(pageable);

        if (filmesRetornados.isEmpty()){
            throw new FilmeNaoExisteException("Filmes ainda não foram cadastrados!");
        }

        Page<FilmeRespostaMinimoDTO> filmeRespostaDTOS = filmesRetornados.map(filme -> Mapeador.converterObjeto(filme, FilmeRespostaMinimoDTO.class));
        filmeRespostaDTOS.forEach(this::adicionaLinks);

        Link link = linkTo(methodOn(FilmeController.class)
                .retornarTodosFilmes(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();

        return assembler.toModel(filmeRespostaDTOS, link);
    }

    public PagedModel<EntityModel<FilmeRespostaMinimoDTO>> retornarFilmesPeloTitulo(String titulo, Pageable pageable){
        Page<Filme> filmesRetornados = repository.findByTituloContainingIgnoreCase(titulo, pageable);

        if (filmesRetornados.isEmpty()){
            throw new FilmeNaoExisteException("Não existem filmes com esse título!!");
        }

        Page<FilmeRespostaMinimoDTO> filmeRespostaDTOS = filmesRetornados.map(filme -> Mapeador.converterObjeto(filme, FilmeRespostaMinimoDTO.class));
        filmeRespostaDTOS.forEach(this::adicionaLinks);

        Link link = linkTo(methodOn(FilmeController.class)
                .retornarTodosFilmes(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();

        return assembler.toModel(filmeRespostaDTOS, link);
    }

    public FilmeRespostaDTO atualizaFilme(FilmeRegistroDTO novoFilme, Long id){
        Optional<Filme> filmeParaAtualizar = repository.findById(id);

        if (filmeParaAtualizar.isEmpty()){
            throw new FilmeNaoExisteException("Filme não encontrado!");
        }
        atualizarDados(filmeParaAtualizar.get(), novoFilme);
        repository.save(filmeParaAtualizar.get());

        FilmeRespostaDTO filmeDeResposta = Mapeador.converterObjeto(filmeParaAtualizar.get(),FilmeRespostaDTO.class);
        adicionaLinks(filmeDeResposta);

        return filmeDeResposta;
    }

    private void atualizarDados(Filme filmeParaAtualizar, FilmeRegistroDTO novoFilme) {
        filmeParaAtualizar.setTitulo(novoFilme.getTitulo());
        filmeParaAtualizar.setDescricao(novoFilme.getDescricao());
        filmeParaAtualizar.setAnoDeLancamento(novoFilme.getAnoDeLancamento());
        filmeParaAtualizar.setUrlDaCapa(novoFilme.getUrlDaCapa());
        filmeParaAtualizar.setTempoEmMinutos(novoFilme.getTempoEmMinutos());
        filmeParaAtualizar.setDiretor(novoFilme.getDiretor());
    }
}
