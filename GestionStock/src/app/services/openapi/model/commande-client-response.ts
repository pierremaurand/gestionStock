/**
 * Gestion de stock REST API
 *
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { ClientResponse } from './client-response';


export interface CommandeClientResponse { 
    id?: number;
    code?: string;
    dateCommande?: string;
    etatCommande?: CommandeClientResponse.EtatCommandeEnum;
    client?: ClientResponse;
}
export namespace CommandeClientResponse {
    export type EtatCommandeEnum = 'EN_PREPARATION' | 'VALIDEE' | 'LIVREE';
    export const EtatCommandeEnum = {
        EnPreparation: 'EN_PREPARATION' as EtatCommandeEnum,
        Validee: 'VALIDEE' as EtatCommandeEnum,
        Livree: 'LIVREE' as EtatCommandeEnum
    };
}


