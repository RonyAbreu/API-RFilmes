package com.ronyelison.filmes.controllers;

import com.ronyelison.filmes.dto.filme.FilmeRegistroDTO;
import com.ronyelison.filmes.dto.filme.FilmeRespostaDTO;
import com.ronyelison.filmes.dto.filme.FilmeRespostaMinimoDTO;
import com.ronyelison.filmes.models.Filme;
import com.ronyelison.filmes.services.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/filmes")
@Tag(name = "Filme", description = "Endpoint de Filmes")
public class FilmeController {
    private FilmeService service;

    @Autowired
    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar Filme",description = "Cadastrar Filme", tags = "Filme",
            responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = FilmeRespostaDTO.class))),
            @ApiResponse(description = "Conflito", responseCode = "409", content = @Content()),
            @ApiResponse(description = "Erro do Usuário", responseCode = "400", content = @Content()),
            @ApiResponse(description = "Não autorizado", responseCode = "403", content = @Content),
            @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FilmeRespostaDTO> cadastrarFilme(@RequestBody @Valid FilmeRegistroDTO filmeRegistroDTO){
        return ResponseEntity.ok(service.cadastrarFilme(filmeRegistroDTO));
    }

    @Operation(summary = "Remover Filme",description = "Remover Filme", tags = "Filme",
            responses = {
                    @ApiResponse(description = "Nenhum Conteúdo", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Filme não encontrado", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Não autorizado", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removerFilme(@PathVariable Long id){
        service.removerFilme(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retornar Filme",description = "Retornar Filme", tags = "Filme",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = Filme.class))),
                    @ApiResponse(description = "Filme não encontrado", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Não autorizado", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FilmeRespostaDTO> retornaFilme(@PathVariable Long id){
        var filme = service.retornaFilme(id);
        return ResponseEntity.ok(filme);
    }

    @Operation(summary = "Retornar todos os Filmes",description = "Retornar todos os Filmes", tags = "Filme",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FilmeRespostaDTO.class)))),
                    @ApiResponse(description = "Filmes não encontrados", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<EntityModel<FilmeRespostaMinimoDTO>>> retornarTodosFilmes(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                                                   @RequestParam(value = "size", defaultValue = "40") Integer size,
                                                                                                   @RequestParam(value = "sort", defaultValue = "asc") String sort){
        var ordenacaoDaPagina = "desc".equalsIgnoreCase(sort) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page,size, Sort.by(ordenacaoDaPagina, "titulo"));
        var filmes = service.retornarTodosFilmes(pageable);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Retornar Filmes pelo Título",description = "Retornar Filmes pelo Título", tags = "Filme",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FilmeRespostaDTO.class)))),
                    @ApiResponse(description = "Filmes não encontrados", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @GetMapping(value = "/busca", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<EntityModel<FilmeRespostaMinimoDTO>>> retornarFilmesPeloTitulo(@RequestParam(value = "titulo", defaultValue = "") String titulo,
                                                                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                                            @RequestParam(value = "size", defaultValue = "40") Integer size,
                                                                                            @RequestParam(value = "sort", defaultValue = "asc") String sort){
        var ordenacaoDaPagina = "desc".equalsIgnoreCase(sort) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(ordenacaoDaPagina, "titulo"));
        var filmes = service.retornarFilmesPeloTitulo(titulo,pageable);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Atualizar Filme",description = "Atualizar Filme", tags = "Filme",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = FilmeRespostaDTO.class))),
                    @ApiResponse(description = "Erro do Usuário", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Filme não encontrado", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Não autorizado", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FilmeRespostaDTO> atualizarFilme(@RequestBody @Valid FilmeRegistroDTO filme, @PathVariable Long id){
        var filmeAtualizado = service.atualizaFilme(filme,id);
        return ResponseEntity.ok(filmeAtualizado);
    }
}
