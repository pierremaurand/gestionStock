package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.common.Sexe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UtilisateurRequest(
        Long id,
        @NotNull(message = "First Name is mandatory")
        @NotEmpty(message = "First Name is mandatory")
        @NotBlank(message = "First Name is mandatory")
        String firstName,
        @NotNull(message = "Last Name is mandatory")
        @NotEmpty(message = "Last Name is mandatory")
        @NotBlank(message = "Last Name is mandatory")
        String lastName,
        @NotNull(message = "Username is mandatory")
        @NotEmpty(message = "Username is mandatory")
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotEmpty(message = "Password is mandatory")
        @NotNull(message = "Password is mandatory")
        @Size(min = 8, message = "Password should be 8 characters long minimum")
        String password,
        @NotNull(message = "Sexe is mandatory")
        @NotEmpty(message = "Sexe is mandatory")
        @NotBlank(message = "Sexe is mandatory")
        Sexe sexe
) {
}
