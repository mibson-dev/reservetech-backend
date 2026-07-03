package com.example.reservetech.config;

import com.example.reservetech.model.PeriodoAula;
import com.example.reservetech.repositories.PeriodoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class PeriodoAulaSeeder implements CommandLineRunner {

    @Autowired
    private PeriodoAulaRepository periodoAulaRepository;

    @Override
    public void run(String... args) {
        if (periodoAulaRepository.count() == 0) {
            periodoAulaRepository.saveAll(List.of(
                    new PeriodoAula(null, "1º Horário", LocalTime.of(18, 30), LocalTime.of(19, 20)),
                    new PeriodoAula(null, "2º Horário", LocalTime.of(19, 20), LocalTime.of(20, 10)),
                    new PeriodoAula(null, "3º Horário", LocalTime.of(20, 20), LocalTime.of(21, 10)),
                    new PeriodoAula(null, "4º Horário", LocalTime.of(21, 10), LocalTime.of(22, 0))
            ));
            System.out.println("Períodos de aula criados.");
        }
    }
}
