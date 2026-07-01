package com.example.reservetech.DTO;

import com.example.reservetech.model.ItemReserva;

public record ItemReservaResponseDTO(
        Long id,
        Long dispositivoId,
        String nomeDispositivo,
        Integer quantidadeReservada
) {
    public ItemReservaResponseDTO(ItemReserva item) {
        this(item.getId(), item.getDispositivo().getId(),
                item.getDispositivo().getNome(), item.getQuantidadeReservada());
    }
}