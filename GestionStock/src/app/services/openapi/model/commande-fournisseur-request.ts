/**
 * Gestion de stock REST API
 *
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { LigneCommandeFournisseurRequest } from './ligne-commande-fournisseur-request';


export interface CommandeFournisseurRequest { 
    id?: number;
    code: string;
    dateCommande: string;
    fournisseur: number;
    ligneCommandeFournisseurs: Array<LigneCommandeFournisseurRequest>;
}

