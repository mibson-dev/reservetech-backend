package com.example.reservetech.services;

import com.example.reservetech.DTO.DispositivoRequestDTO;
import com.example.reservetech.DTO.DispositivoResponseDTO;
import com.example.reservetech.exceptions.DispositivoNaoEncontradoException;
import com.example.reservetech.model.Dispositivo;
import com.example.reservetech.model.StatusDispositivo;
import com.example.reservetech.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public DispositivoResponseDTO criar(DispositivoRequestDTO dto) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setNome(dto.nome());
        dispositivo.setDescricao(dto.descricao());
        dispositivo.setQuantidadeDisponivel(dto.quantidadeDisponivel());
        dispositivo.setStatus(dto.status());

        dispositivoRepository.save(dispositivo);
        return new DispositivoResponseDTO(dispositivo);
    }

    public Page<DispositivoResponseDTO> listarTodos(Pageable pageable) {
        return dispositivoRepository.findAll(pageable)
                .map(DispositivoResponseDTO::new);
    }

    public Page<DispositivoResponseDTO> listarPorStatus(StatusDispositivo status, Pageable pageable) {
        return dispositivoRepository.findByStatus(status, pageable)
                .map(DispositivoResponseDTO::new);
    }

    public DispositivoResponseDTO buscarPorId(Long id) {
        Dispositivo dispositivo = buscarEntidadePorId(id);
        return new DispositivoResponseDTO(dispositivo);
    }

    public DispositivoResponseDTO atualizar(Long id, DispositivoRequestDTO dto) {
        Dispositivo dispositivo = buscarEntidadePorId(id);
        dispositivo.setNome(dto.nome());
        dispositivo.setDescricao(dto.descricao());
        dispositivo.setQuantidadeDisponivel(dto.quantidadeDisponivel());
        dispositivo.setStatus(dto.status());

        dispositivoRepository.save(dispositivo);
        return new DispositivoResponseDTO(dispositivo);
    }

    public void deletar(Long id) {
        Dispositivo dispositivo = buscarEntidadePorId(id);
        dispositivoRepository.delete(dispositivo);
    }

    private Dispositivo buscarEntidadePorId(Long id) {
        return dispositivoRepository.findById(id)
                .orElseThrow(() -> new DispositivoNaoEncontradoException("Dispositivo não encontrado"));
    }
}