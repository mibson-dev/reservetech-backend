package com.example.reservetech.controller;

import com.example.reservetech.DTO.DispositivoRequestDTO;
import com.example.reservetech.DTO.DispositivoResponseDTO;
import com.example.reservetech.model.StatusDispositivo;
import com.example.reservetech.services.DispositivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<DispositivoResponseDTO> criar(@RequestBody @Valid DispositivoRequestDTO dto) {
        return ResponseEntity.status(201).body(dispositivoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<DispositivoResponseDTO>> listarTodos(
            @RequestParam(required = false) StatusDispositivo status,
            Pageable pageable
    ) {
        if (status != null) {
            return ResponseEntity.ok(dispositivoService.listarPorStatus(status, pageable));
        }
        return ResponseEntity.ok(dispositivoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(dispositivoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid DispositivoRequestDTO dto) {
        return ResponseEntity.ok(dispositivoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        dispositivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}