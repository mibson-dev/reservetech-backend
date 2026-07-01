package com.example.reservetech.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemReservaRequestDTO(
        @NotNull Long dispositivoId,
        @NotNull @Positive Integer quantidadeReservada
) {
}
