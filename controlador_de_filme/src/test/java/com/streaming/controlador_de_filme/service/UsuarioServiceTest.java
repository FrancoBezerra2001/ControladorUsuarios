package com.streaming.controlador_de_filme.service;

import com.streaming.controlador_de_filme.dto.UsuarioRequestDTO;
import com.streaming.controlador_de_filme.dto.UsuarioResponseDTO;
import com.streaming.controlador_de_filme.model.Plano;
import com.streaming.controlador_de_filme.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        usuarioService = new UsuarioService(usuarioRepository, passwordEncoder);
    }

    @Test
    @DisplayName("Deve cadastrar um usuário com sucesso quando atender a todos os requisitos")
    void deveCadastrarUsuarioComSucesso() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("Alex Silva");
        dto.setEmail("alex@email.com");
        dto.setSenha("123456");
        dto.setDataNascimento(LocalDate.now().minusYears(20));
        dto.setPlano(Plano.PREMIUM);

        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(dto.getSenha())).thenReturn("senhaCriptografada");
        when(usuarioRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        UsuarioResponseDTO resultado = usuarioService.cadastrarUsuario(dto);

        assertNotNull(resultado);
        assertEquals("alex@email.com", resultado.getEmail());
    }

    @Test
    @DisplayName("Deve lancar excecao se o usuario for menor de 12 anos")
    void deveLancarExcecaoParaMenorDeDozeAnos() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("Lucas Menor");
        dto.setEmail("lucas@email.com");
        dto.setSenha("123456");
        dto.setDataNascimento(LocalDate.now().minusYears(10));
        dto.setPlano(Plano.BASICO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrarUsuario(dto);
        });

        assertEquals("Usuários menores de 12 anos não podem possuir conta titular.", exception.getMessage());
    }
}
