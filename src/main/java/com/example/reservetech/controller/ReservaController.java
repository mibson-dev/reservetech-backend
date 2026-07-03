package com.example.reservetech.controller;


import com.example.reservetech.DTO.AtualizarStatusReservaDTO;
import com.example.reservetech.DTO.ReservaRequestDTO;
import com.example.reservetech.DTO.ReservaResponseDTO;
import com.example.reservetech.DTO.ReservaUpdateDTO;
import com.example.reservetech.model.StatusReserva;
import com.example.reservetech.model.Usuario;
import com.example.reservetech.services.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar(
            @RequestBody @Valid ReservaRequestDTO dto,
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        return ResponseEntity.status(201).body(reservaService.criar(dto, usuarioLogado));
    }

    @GetMapping("/minhas")
    public ResponseEntity<Page<ReservaResponseDTO>> listarMinhasReservas(
            @AuthenticationPrincipal Usuario usuarioLogado,
            @RequestParam(required = false) String periodo,
            Pageable pageable
    ) {
        if (periodo != null) {
            return ResponseEntity.ok(reservaService.listarMinhasReservasPorPeriodo(usuarioLogado.getId(), periodo, pageable));
        }
        return ResponseEntity.ok(reservaService.listarMinhasReservas(usuarioLogado.getId(), pageable));
    }

    @GetMapping
    public ResponseEntity<Page<ReservaResponseDTO>> listarTodas(
            @RequestParam(required = false) StatusReserva status,
            Pageable pageable
    ) {
        if (status != null) {
            return ResponseEntity.ok(reservaService.listarPorStatus(status, pageable));
        }
        return ResponseEntity.ok(reservaService.listarTodas(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarStatusReservaDTO dto
    ) {
        return ResponseEntity.ok(reservaService.atualizarStatus(id, dto.status()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ReservaUpdateDTO dto
    ) {
        return ResponseEntity.ok(reservaService.atualizar(id, dto));
    }
}