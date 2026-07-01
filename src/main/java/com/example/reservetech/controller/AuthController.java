package com.example.reservetech.controller;

import com.example.reservetech.DTO.LoginRequestDTO;
import com.example.reservetech.DTO.UsuarioRequestDTO;
import com.example.reservetech.DTO.UsuarioResponseDTO;
import com.example.reservetech.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody @Valid UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuarioCriado = authService.registrar(dto);
        return ResponseEntity.status(201).body(usuarioCriado);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO dto) {
        String token = authService.login(dto.email(), dto.senha());
        return ResponseEntity.ok(token);
    }
}