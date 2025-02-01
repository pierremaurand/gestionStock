package com.opmg.ApiGestionStock.fournisseur;

import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.file.FileStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FournisseurService {
    private final FournisseurRepository repository;
    private final FournisseurMapper mapper;
    private final FileStorageService fileStorageService;

    public Long save(@Valid FournisseurRequest request) {
        if (repository.existsByNumeroCNI(request.numeroCNI())) {
            log.error("Fournisseur already exist {}", request);
            throw new InvalidEntityException(FOURNISSEUR_ALREADY_EXISTS);
        }
        return repository.save(mapper.toFournisseur(request)).getId();
    }

    public PageResponse<FournisseurResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Fournisseur> fournisseurs = repository.findAll(pageable);
        List<FournisseurResponse> fournisseursResponse = fournisseurs.stream().map(mapper::toFournisseurResponse).toList();
        return new PageResponse<>(
                fournisseursResponse,
                fournisseurs.getNumber(),
                fournisseurs.getSize(),
                fournisseurs.getTotalElements(),
                fournisseurs.getTotalPages(),
                fournisseurs.isFirst(),
                fournisseurs.isLast()
        );
    }

    public FournisseurResponse findById(Long fournisseurId) {
        return mapper.toFournisseurResponse(getFournisseurById(fournisseurId));
    }

    public FournisseurResponse findByNumeroCNI(String numeroCNI) {
        return mapper.toFournisseurResponse(getFournisseurByNumeroCNI(numeroCNI));
    }

    public FournisseurResponse findByNumeroTel(String numeroTel) {
        return mapper.toFournisseurResponse(getFournisseurByNumeroTel(numeroTel));
    }

    public FournisseurResponse findByEmail(String email) {
        return mapper.toFournisseurResponse(getFournisseurByEmail(email));
    }

    public void savePhoto(MultipartFile file, Long id) {
        Fournisseur fournisseur = getFournisseurById(id);
        var photo = fileStorageService.saveFile(file, "fournisseur", String.valueOf(id));
        fournisseur.setPhoto(photo);
        repository.save(fournisseur);
    }

    public Fournisseur getFournisseurByNumeroCNI(String numeroCNI){
        return repository.findByNumeroCNI(numeroCNI)
                .orElseThrow(() -> new EntityNotFoundException(FOURNISSEUR_NOT_FOUND));
    }

    public Fournisseur getFournisseurByNumeroTel(String numeroTel){
        return repository.findByNumeroTel(numeroTel)
                .orElseThrow(() -> new EntityNotFoundException(FOURNISSEUR_NOT_FOUND));
    }

    public Fournisseur getFournisseurByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(FOURNISSEUR_NOT_FOUND));
    }

    public Fournisseur getFournisseurById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(FOURNISSEUR_NOT_FOUND));
    }

    public void delete(Long id){
        Fournisseur fournisseur = getFournisseurById(id);
        if(fournisseur.getCommandeFournisseurs().isEmpty()){
            repository.deleteById(id);
        }
    }
}
