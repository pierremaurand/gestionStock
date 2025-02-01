package com.opmg.ApiGestionStock.categorie;

import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.handler.BusinessErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.CATEGORIE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategorieService {
    private final CategorieRepository repository;
    private final CategorieMapper mapper;

    public Long save(CategorieRequest request) {
        boolean exists = repository.existsByCode(request.code());
        if (exists) {
            log.error("Categorie already exists in the data base");
            throw new InvalidEntityException(BusinessErrorCodes.CODE_CATEGORIE_ALREADY_EXISTS);
        }
        Categorie categorie = mapper.toCategorie(request);
        return repository.save(categorie).getId();
    }

    public PageResponse<CategorieResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Categorie> categories = repository.findAll(pageable);
        List<CategorieResponse> categoriesResponse = categories.stream().map(mapper::toCategorieResponse).toList();
        return new PageResponse<>(
                categoriesResponse,
                categories.getNumber(),
                categories.getSize(),
                categories.getTotalElements(),
                categories.getTotalPages(),
                categories.isFirst(),
                categories.isLast()
        );
    }

    public CategorieResponse findById(Long id) {
        return mapper.toCategorieResponse(getCategorieById(id));
    }

    public CategorieResponse findByCode(String code) {
        return mapper.toCategorieResponse(getCategorieByCode(code));
    }

    public Categorie getCategorieById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORIE_NOT_FOUND));
    }

    public Categorie getCategorieByCode(String code){
        return repository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORIE_NOT_FOUND));
    }

    public void delete(Long id){
        Categorie categorie = getCategorieById(id);
        if(categorie.getArticlesIds().isEmpty()){
            repository.deleteById(id);
        }
    }
}
