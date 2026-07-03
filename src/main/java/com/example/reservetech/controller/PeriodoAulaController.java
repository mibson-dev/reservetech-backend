package com.example.reservetech.controller;

import com.example.reservetech.DTO.PeriodoAulaResponseDTO;
import com.example.reservetech.repositories.PeriodoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/periodos")
public class PeriodoAulaController {

    @Autowired
    private PeriodoAulaRepository periodoAulaRepository;

    @GetMapping
    public ResponseEntity<List<PeriodoAulaResponseDTO>> listar() {
        return ResponseEntity.ok(
                periodoAulaRepository.findAll()
                        .stream()
                        .map(PeriodoAulaResponseDTO::new)
                        .toList()
        );
    }
}