package com.ronyelison.filmes.controllers;

import com.ronyelison.filmes.dto.usuario.UsuarioDeRespostaDTO;
import com.ronyelison.filmes.services.UsuarioAutenticadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Usuario Autenticado", description = "Endpoints para Usuários Autenticados")
public class UsuarioAutenticadoController {
    private UsuarioAutenticadoService service;

    @Autowired
    public UsuarioAutenticadoController(UsuarioAutenticadoService service) {
        this.service = service;
    }
    @Operation(summary = "Retornar Usuário",description = "Retornar Usuário", tags = "Usuario Autenticado",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = UsuarioDeRespostaDTO.class))),
                    @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Não autorizado", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UsuarioDeRespostaDTO> retornarUsuario(@PathVariable Long id){
        return ResponseEntity.ok(service.retornarUsuario(id));
    }

    @Operation(summary = "Remover Usuário",description = "Remover Usuário", tags = "Usuario Autenticado",
            responses = {
                    @ApiResponse(description = "Sem conteúdo", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Não autorizado", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Long id){
        service.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
