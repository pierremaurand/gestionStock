package com.opmg.ApiGestionStock.commandeClient;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChangeClientRequest(
        @NotNull(message = "1300")
        @Min(value = 1, message = "1301")
        Long commandeClient,
        @NotNull(message = "1302")
        @Min(value = 1, message = "1303")
        Long client
) {
}
