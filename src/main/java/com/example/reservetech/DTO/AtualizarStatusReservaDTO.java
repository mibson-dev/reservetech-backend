package com.example.reservetech.DTO;

import com.example.reservetech.model.StatusReserva;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusReservaDTO(
        @NotNull StatusReserva status
) {
}
