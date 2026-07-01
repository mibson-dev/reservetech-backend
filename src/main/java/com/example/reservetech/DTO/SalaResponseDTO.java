package com.example.reservetech.DTO;

import com.example.reservetech.model.Sala;

public record SalaResponseDTO(
        Long id,
        String nome,
        String andar
) {
    public SalaResponseDTO(Sala sala) {
        this(sala.getId(), sala.getNome(), sala.getAndar());
    }
}