package com.streaming.controlador_de_filme.controller;

import com.streaming.controlador_de_filme.dto.UsuarioRequestDTO;
import com.streaming.controlador_de_filme.dto.UsuarioResponseDTO;
import com.streaming.controlador_de_filme.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO response = usuarioService.cadastrarUsuario(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}