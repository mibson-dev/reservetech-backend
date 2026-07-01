package com.example.reservetech.controller;


import com.example.reservetech.DTO.SalaRequestDTO;
import com.example.reservetech.DTO.SalaResponseDTO;
import com.example.reservetech.services.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<SalaResponseDTO> criar(@RequestBody @Valid SalaRequestDTO dto) {
        return ResponseEntity.status(201).body(salaService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<SalaResponseDTO>> listarTodas(Pageable pageable) {
        return ResponseEntity.ok(salaService.listarTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid SalaRequestDTO dto) {
        return ResponseEntity.ok(salaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        salaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
