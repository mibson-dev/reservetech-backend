package com.example.reservetech.DTO;

import com.example.reservetech.model.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotNull PerfilUsuario perfil
) {
}