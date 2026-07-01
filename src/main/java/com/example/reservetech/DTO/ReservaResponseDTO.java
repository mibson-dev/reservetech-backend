package com.example.reservetech.DTO;

import com.example.reservetech.model.Reserva;
import com.example.reservetech.model.StatusReserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ReservaResponseDTO(
        Long id,
        String nomeUsuario,
        String nomeSala,
        LocalDate dataReserva,
        LocalTime horarioInicio,
        LocalTime horarioFim,
        StatusReserva status,
        List<ItemReservaResponseDTO> itens
) {
    public ReservaResponseDTO(Reserva reserva) {
        this(
                reserva.getId(),
                reserva.getUsuario().getNome(),
                reserva.getSala().getNome(),
                reserva.getDataReserva(),
                reserva.getHorarioInicio(),
                reserva.getHorarioFim(),
                reserva.getStatus(),
                reserva.getItens().stream().map(ItemReservaResponseDTO::new).toList()
        );
    }
}
