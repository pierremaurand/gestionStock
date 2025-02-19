package com.opmg.ApiGestionStock.auth;

import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.role.Role;
import com.opmg.ApiGestionStock.role.RoleRepository;
import com.opmg.ApiGestionStock.utilisateur.*;
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
    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthenticationResponse verify(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.login(), request.motDePasse())
        );

        UtilisateurResponse utilisateur = mapper.toUtilisateurResponse(
                utilisateurService.getUtilisateurByLogin(request.login())
        );

        if (authentication.isAuthenticated()) {
            return AuthenticationResponse.builder()
                    .utilisateur(utilisateur)
                    .token(jwtService.generateToken(request.login()))
                    .build();
        }
        return null;
    }
}
