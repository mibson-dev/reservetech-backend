package com.example.reservetech.DTO;

import com.example.reservetech.model.StatusDispositivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DispositivoRequestDTO(
        @NotBlank String nome,
        String descricao,
        @NotNull @PositiveOrZero Integer quantidadeDisponivel,
        @NotNull StatusDispositivo status
) {
}

