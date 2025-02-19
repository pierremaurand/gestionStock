package com.opmg.ApiGestionStock.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),

    ARTICLE_NOT_FOUND(1000, HttpStatus.NOT_FOUND, "Article introuvable"),
    ARTICLE_OPERATION_NOT_PERMIT(1001, HttpStatus.FORBIDDEN, "La suppression de cet article n'est pas permit"),
    CODE_ARTICLE_ALREADY_EXISTS(102, HttpStatus.BAD_REQUEST, "Article code already exists"),

    CATEGORIE_NOT_FOUND(2000, HttpStatus.NOT_FOUND, "Catégorie introuvable"),
    CATEGORIE_OPERATION_NOT_PERMIT(2001, HttpStatus.FORBIDDEN, "La suppression de cette catégorie n'est pas permit"),
    CODE_CATEGORIE_ALREADY_EXISTS(2002, HttpStatus.BAD_REQUEST, "Ce code catégorie est déjà utilisé"),

    CLIENT_NOT_FOUND(3000, HttpStatus.NOT_FOUND, "Client not found"),
    CLIENT_NOT_VALID(3001, HttpStatus.BAD_REQUEST, "Client not valid"),
    CLIENT_ALREADY_EXISTS(3002, HttpStatus.BAD_REQUEST, "Client already exists"),

    ROLE_NOT_FOUND(4000, HttpStatus.NOT_FOUND, "Role not found"),
    ROLE_NOT_VALID(4001, HttpStatus.BAD_REQUEST, "ROle not valid"),
    ROLE_ALREADY_EXISTS(4002, HttpStatus.BAD_REQUEST, "Role already exists"),

    UTILISATEUR_NOT_FOUND(5000, HttpStatus.NOT_FOUND, "Utilisateur not found"),
    UTILISATEUR_NOT_VALID(5001, HttpStatus.BAD_REQUEST, "Utilisateur not valid"),
    USERNAME_ALREADY_EXISTS(5002, HttpStatus.BAD_REQUEST, "Username already exists"),

    COMMANDE_CLIENT_NOT_FOUND(6000, HttpStatus.NOT_FOUND, "Code commande client not found"),
    CODE_COMMANDE_CLIENT_ALREADY_EXISTS(6001, HttpStatus.BAD_REQUEST, "Code commande client already exists"),
    COMMANDE_CLIENT_CANNOT_BE_MODIFIED(6002, HttpStatus.BAD_REQUEST, "Commande client cannot be modified"),
    LIGNE_DOES_NOT_BELONG_TO_THIS_COMMANDE_CLIENT(6003,HttpStatus.BAD_REQUEST,"Ligne does not belong to this commande client"),

    COMMANDE_FOURNISSEUR_NOT_FOUND(7000, HttpStatus.NOT_FOUND, "Commande fournisseur not found"),
    CODE_COMMANDE_FOURNISSEUR_ALREADY_EXISTS(7001, HttpStatus.BAD_REQUEST, "Code commande fournisseur already exists"),
    COMMANDE_FOURNISSEUR_CANNOT_BE_MODIFIED(7002, HttpStatus.BAD_REQUEST, "Commande fournisseur cannot be modified"),
    LIGNE_DOES_NOT_BELONG_TO_THIS_COMMANDE_FOURNISSEUR(7003,HttpStatus.BAD_REQUEST,"Ligne does not belong to this commande fournisseur"),

    FOURNISSEUR_NOT_FOUND(8000, HttpStatus.NOT_FOUND, "Fournisseur not found"),
    FOURNISSEUR_NOT_VALID(8001, HttpStatus.BAD_REQUEST, "Fournisseur not valid"),
    FOURNISSEUR_ALREADY_EXISTS(8002, HttpStatus.BAD_REQUEST, "Fournisseur already exists"),

    LIGNE_COMMANDE_CLIENT_NOT_FOUND(9000, HttpStatus.NOT_FOUND, "Ligne commande client not found"),
    LIGNE_COMMANDE_CLIENT_NOT_VALID(9001, HttpStatus.BAD_REQUEST, "Ligne commande client not valid"),

    LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND(10000, HttpStatus.NOT_FOUND, "Ligne commande fournisseur not found"),
    LIGNE_COMMANDE_FOURNISSEUR_NOT_VALID(10001, HttpStatus.BAD_REQUEST, "Ligne commande fournisseur not valid"),

    LIGNE_VENTE_NOT_FOUND(11000, HttpStatus.NOT_FOUND, "Ligne vente not found"),
    LIGNE_VENTE_NOT_VALID(11001, HttpStatus.BAD_REQUEST, "Ligne vente not valid"),

    VENTE_NOT_FOUND(12000, HttpStatus.NOT_FOUND, "Vente not found"),
    VENTE_NOT_VALID(12001, HttpStatus.BAD_REQUEST, "Vente not valid"),

    MOUVEMENT_STOCK_NOT_FOUND(13000, HttpStatus.NOT_FOUND, "Mouvement stock not found"),

    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Current password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "The new password does not match"),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN, "Login and / or Password is incorrect"),
    USERNAME_NOT_FOUND(305, HttpStatus.BAD_REQUEST, "Username not found"),

    ;

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.httpStatus = status;
        this.description = description;
    }
}
