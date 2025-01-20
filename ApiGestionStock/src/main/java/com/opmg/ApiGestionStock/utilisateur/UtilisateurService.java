package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.role.Role;
import com.opmg.ApiGestionStock.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilisateurService {
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    public void init() {
        if(utilisateurRepository.findAll().isEmpty()) {
            Optional<Role> adminRole = roleRepository.findByName("ADMIN");
            adminRole.ifPresent(role -> utilisateurRepository.save(
                    Utilisateur.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin"))
                            .roles(List.of(role))
                            .build())
            );
        }
    }
}
