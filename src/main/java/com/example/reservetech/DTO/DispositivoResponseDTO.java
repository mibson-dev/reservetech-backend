package com.example.reservetech.DTO;

import com.example.reservetech.model.Dispositivo;
import com.example.reservetech.model.StatusDispositivo;

public record DispositivoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Integer quantidadeDisponivel,
        StatusDispositivo status
) {
    public DispositivoResponseDTO(Dispositivo dispositivo) {
        this(dispositivo.getId(), dispositivo.getNome(), dispositivo.getDescricao(),
                dispositivo.getQuantidadeDisponivel(), dispositivo.getStatus());
    }
}
