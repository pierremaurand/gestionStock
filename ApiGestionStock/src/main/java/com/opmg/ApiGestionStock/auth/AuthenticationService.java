package com.opmg.ApiGestionStock.auth;

import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.role.Role;
import com.opmg.ApiGestionStock.role.RoleRepository;
import com.opmg.ApiGestionStock.utilisateur.Utilisateur;
import com.opmg.ApiGestionStock.utilisateur.UtilisateurRepository;
import com.opmg.ApiGestionStock.service.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.ROLE_NOT_FOUND;
import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.USERNAME_ALREADY_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public String verify(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }
        return "Fail";
    }

    public void register(RegisterRequest request) {
        if(utilisateurRepository.existsByUsername(request.username())) {
            log.error("This username is already use USERNAME::{}", request.username());
            throw new InvalidEntityException(USERNAME_ALREADY_EXISTS);
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(()->new EntityNotFoundException(ROLE_NOT_FOUND));

        Utilisateur utilisateur = Utilisateur.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .roles(List.of(userRole))
                .build();
        utilisateurRepository.save(utilisateur);
    }
}
