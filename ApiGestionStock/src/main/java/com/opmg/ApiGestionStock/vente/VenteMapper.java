package com.opmg.ApiGestionStock.vente;

import org.springframework.stereotype.Service;

@Service
public class VenteMapper {
    public Vente toVente(VenteRequest request){
        return Vente.builder()
                .code(request.code())
                .dateVente(request.dateVente())
                .build();
    }

    public VenteResponse toVenteResponse(Vente vente){
        return VenteResponse.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .build();
    }
}
