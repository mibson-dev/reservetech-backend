package com.example.reservetech.config;

import com.example.reservetech.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservaScheduler {

    @Autowired
    private ReservaService reservaService;

    @Scheduled(fixedRate = 300000)
    public void cancelarReservasExpiradas() {
        int canceladas = reservaService.expirarReservasPendentes();
        if (canceladas > 0) {
            System.out.println("[Scheduler] Reservas expiradas canceladas automaticamente: " + canceladas);
        }
    }
}
