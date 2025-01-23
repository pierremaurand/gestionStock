package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.exception.InvalidOperationException;
import com.opmg.ApiGestionStock.file.FileStorageService;
import com.opmg.ApiGestionStock.role.Role;
import com.opmg.ApiGestionStock.role.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilisateurService {
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository repository;
    private final UtilisateurMapper mapper;
    private final RoleService roleService;
    private final FileStorageService fileStorageService;

    public Long save(UtilisateurRequest request){
        if(repository.existsByLogin(request.login())){
            log.error("Login is already use");
            throw new InvalidEntityException(UTILISATEUR_NOT_VALID);
        }
        Utilisateur utilisateur = mapper.toUtilisateur(request);
        Role userRole = roleService.getRoleByName("USER");
        utilisateur.setRoles(List.of(userRole));
        utilisateur.setMotDePasse(passwordEncoder.encode("user"));
        return repository.save(utilisateur).getId();
    }

    public UtilisateurResponse findById(Long id){
        return mapper.toUtilisateurResponse(getUtilisateurById(id));
    }

    public UtilisateurResponse findByLogin(String login){
        return mapper.toUtilisateurResponse(getUtilisateurByLogin(login));
    }

    public PageResponse<UtilisateurResponse> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Utilisateur> utilisateurs = repository.findAll(pageable);
        List<UtilisateurResponse> utilisateurResponses = utilisateurs.stream().map(mapper::toUtilisateurResponse).toList();
        return new PageResponse<>(
                utilisateurResponses,
                utilisateurs.getNumber(),
                utilisateurs.getSize(),
                utilisateurs.getTotalElements(),
                utilisateurs.isFirst(),
                utilisateurs.isLast()
        );
    }

    public Long changePassword(ChangePasswordRequest request){
        Utilisateur utilisateur = getUtilisateurById(request.utilisateur());
        if(!request.motDePasse().equals(request.confirmMotDePasse())){
            log.error("New Password does not match");
            throw new InvalidOperationException(NEW_PASSWORD_DOES_NOT_MATCH);
        }
        utilisateur.setMotDePasse(passwordEncoder.encode(request.motDePasse()));
        return repository.save(utilisateur).getId();
    }

    public void savePhoto(MultipartFile file, Long id) {
        Utilisateur utilisateur = getUtilisateurById(id);
        var photo = fileStorageService.saveFile(file, "utilisateur", String.valueOf(id));
        utilisateur.setPhoto(photo);
        repository.save(utilisateur);
    }

    public void init() {
        if(repository.findAll().isEmpty()) {
            Role adminRole = roleService.getRoleByName("ADMIN");
            repository.save(
                    Utilisateur.builder()
                            .login("admin")
                            .motDePasse(passwordEncoder.encode("admin"))
                            .roles(List.of(adminRole))
                            .build()
            );
        }
    }

    public Utilisateur getUtilisateurById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UTILISATEUR_NOT_FOUND));
    }

    public Utilisateur getUtilisateurByLogin(String login){
        return repository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(UTILISATEUR_NOT_FOUND));
    }
}
