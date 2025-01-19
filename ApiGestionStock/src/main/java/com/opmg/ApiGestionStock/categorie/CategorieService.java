package com.opmg.ApiGestionStock.categorie;

import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.file.FileStorageService;
import com.opmg.ApiGestionStock.handler.BusinessErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.CATEGORIE_NOT_FOUND;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategorieService {
    private final CategorieRepository categorieRepository;
    private final FileStorageService fileStorageService;
    private final CategorieMapper categorieMapper;

    public Long save(CategorieRequest request) {
        boolean exists = categorieRepository.existsByCode(request.code());
        if (exists) {
            log.error("Categorie already exists in the data base");
            throw new InvalidEntityException(BusinessErrorCodes.CODE_CATEGORIE_ALREADY_EXISTS);
        }
        Categorie categorie = categorieMapper.toCategorie(request);
        return categorieRepository.save(categorie).getId();
    }

    public PageResponse<CategorieResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Categorie> categories = categorieRepository.findAll(pageable);
        List<CategorieResponse> categoriesResponse = categories.stream().map(categorieMapper::toCategorieResponse).toList();
        return new PageResponse<>(
                categoriesResponse,
                categories.getNumber(),
                categories.getSize(),
                categories.getTotalElements(),
                categories.isFirst(),
                categories.isLast()
        );
    }

    public CategorieResponse findById(Long categorieId) {
        return categorieRepository.findById(categorieId)
                .map(categorieMapper::toCategorieResponse)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORIE_NOT_FOUND));
    }

    public CategorieResponse findByCode(String code) {
        return categorieRepository.findByCode(code)
                .map(categorieMapper::toCategorieResponse)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORIE_NOT_FOUND));
    }

    public void uploadPicture(MultipartFile file, Long categorieId) {
        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow();
        var photo = fileStorageService.saveFile(file, "categorie", String.valueOf(categorieId));
        categorie.setPhoto(photo);
        categorieRepository.save(categorie);
    }
}
