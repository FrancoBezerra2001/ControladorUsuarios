package com.streaming.controlador_de_filme.dto;

import com.streaming.controlador_de_filme.model.Plano;
import com.streaming.controlador_de_filme.model.Role;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Plano plano;
    private Role role;
}