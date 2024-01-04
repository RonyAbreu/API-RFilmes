package com.ronyelison.filmes.controllers;

import com.ronyelison.filmes.dto.usuario.TokenDTO;
import com.ronyelison.filmes.dto.usuario.UsuarioDeLogin;
import com.ronyelison.filmes.dto.usuario.UsuarioDeRegistroDTO;
import com.ronyelison.filmes.dto.usuario.UsuarioDeRespostaDTO;
import com.ronyelison.filmes.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Endpoints para Usuários")
public class UsuarioController {
    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Cadastrar Usuário",description = "Cadastrar Usuário", tags = "Usuario",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = UsuarioDeRespostaDTO.class))),
                    @ApiResponse(description = "Conflito", responseCode = "409", content = @Content()),
                    @ApiResponse(description = "Erro do Usuário", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Não autorizado", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @PostMapping(value = "/registro", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UsuarioDeRespostaDTO> cadastrarUsuario(@RequestBody @Valid UsuarioDeRegistroDTO usuarioDeRegistroDTO){
        return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuarioDeRegistroDTO));
    }

    @Operation(summary = "Logar Usuário",description = "Logar Usuário", tags = "Usuario",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = TokenDTO.class))),
                    @ApiResponse(description = "Erro do Usuário", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Erro do Servidor", responseCode = "500", content = @Content())})
    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TokenDTO> logarUsuario(@RequestBody @Valid UsuarioDeLogin usuarioDeLogin){
        return ResponseEntity.ok(usuarioService.logarUsuario(usuarioDeLogin));
    }
}
