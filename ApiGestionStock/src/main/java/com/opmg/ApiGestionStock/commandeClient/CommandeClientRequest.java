package com.opmg.ApiGestionStock.commandeClient;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CommandeClientRequest(
        Long id,
        @NotNull(message = "600")
        @NotEmpty(message = "600")
        String code,
        @NotNull(message = "601")
        @NotEmpty(message = "601")
        LocalDate dateCommande,
        @NotNull(message = "601")
        @NotEmpty(message = "601")
        Long client
) {
}
