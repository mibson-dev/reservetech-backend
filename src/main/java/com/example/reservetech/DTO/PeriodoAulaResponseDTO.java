package com.example.reservetech.DTO;

import com.example.reservetech.model.PeriodoAula;

import java.time.LocalTime;

public record PeriodoAulaResponseDTO(
        Long id,
        String descricao,
        LocalTime horarioInicio,
        LocalTime horarioFim
) {
    public PeriodoAulaResponseDTO(PeriodoAula periodo) {
        this(periodo.getId(), periodo.getDescricao(),
                periodo.getHorarioInicio(), periodo.getHorarioFim());
    }
}
