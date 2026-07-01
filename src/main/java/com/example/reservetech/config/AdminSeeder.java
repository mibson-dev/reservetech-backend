package com.example.reservetech.config;

import com.example.reservetech.model.PerfilUsuario;
import com.example.reservetech.model.Usuario;
import com.example.reservetech.repositories.UsuarioRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        boolean existeAdmin = usuarioRepository.findAll()
                .stream()
                .anyMatch(u -> u.getPerfil() == PerfilUsuario.TI);

        if (!existeAdmin) {
            Usuario admin = new Usuario(
                    "Administrador",
                    adminEmail,
                    passwordEncoder.encode(adminPassword),
                    PerfilUsuario.TI
            );
            usuarioRepository.save(admin);
            System.out.println("Admin criado: " + adminEmail);
        }
    }
}
