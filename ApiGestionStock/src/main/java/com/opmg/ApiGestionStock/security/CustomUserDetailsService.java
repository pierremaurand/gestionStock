package com.opmg.ApiGestionStock.security;

import com.opmg.ApiGestionStock.utilisateur.Utilisateur;
import com.opmg.ApiGestionStock.utilisateur.UtilisateurRepository;
import com.opmg.ApiGestionStock.utilisateur.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.getUtilisateurByLogin(login);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(utilisateur);
    }
}
