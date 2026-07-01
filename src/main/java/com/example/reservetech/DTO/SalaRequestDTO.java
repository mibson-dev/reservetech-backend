package com.example.reservetech.DTO;

import jakarta.validation.constraints.NotBlank;

public record SalaRequestDTO(
        @NotBlank String nome,
        @NotBlank String andar
) {
}