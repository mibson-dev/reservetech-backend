package com.example.reservetech.repositories;

import com.example.reservetech.model.Dispositivo;
import com.example.reservetech.model.StatusDispositivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    Page<Dispositivo> findByStatus(StatusDispositivo status, Pageable pageable);
}
