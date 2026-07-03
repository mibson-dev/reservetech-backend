package com.example.reservetech.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ReservaUpdateDTO(
        @NotNull Long salaId,
        @NotNull LocalDate dataReserva,
        @NotNull LocalTime horarioInicio,
        @NotNull LocalTime horarioFim,
        @NotEmpty @Valid List<ItemReservaRequestDTO> itens
) {
}
