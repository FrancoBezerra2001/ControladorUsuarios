package com.streaming.controlador_de_filme.service;

import com.streaming.controlador_de_filme.dto.UsuarioRequestDTO;
import com.streaming.controlador_de_filme.dto.UsuarioResponseDTO;
import com.streaming.controlador_de_filme.model.Role;
import com.streaming.controlador_de_filme.model.Usuario;
import com.streaming.controlador_de_filme.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado no sistema.");
        }

        // Validação TDD: Regra da idade mínima (12 anos)
        if (Period.between(dto.getDataNascimento(), LocalDate.now()).getYears() < 12) {
            throw new IllegalArgumentException("Usuários menores de 12 anos não podem possuir conta titular.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setPlano(dto.getPlano());
        usuario.setRole(Role.ROLE_USER);

        Usuario salvo = usuarioRepository.save(usuario);
        return converteParaDTO(salvo);
    }

    private UsuarioResponseDTO converteParaDTO(Usuario usuario) {
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setDataNascimento(usuario.getDataNascimento());
        response.setPlano(usuario.getPlano());
        response.setRole(usuario.getRole());
        return response;
    }
}
