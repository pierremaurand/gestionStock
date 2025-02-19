/**
 * Gestion de stock REST API
 *
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { HttpHeaders }                                       from '@angular/common/http';

import { Observable }                                        from 'rxjs';

import { ChangePasswordRequest } from '../model/models';
import { PageResponseUtilisateurResponse } from '../model/models';
import { UtilisateurRequest } from '../model/models';
import { UtilisateurResponse } from '../model/models';


import { Configuration }                                     from '../configuration';



export interface UtilisateurServiceInterface {
    defaultHeaders: HttpHeaders;
    configuration: Configuration;

    /**
     * 
     * 
     * @param changePasswordRequest 
     */
    changeUtilisateurPassword(changePasswordRequest: ChangePasswordRequest, extraHttpRequestParams?: any): Observable<number>;

    /**
     * 
     * 
     * @param page 
     * @param size 
     */
    findAllUtilisateurs(page?: number, size?: number, extraHttpRequestParams?: any): Observable<PageResponseUtilisateurResponse>;

    /**
     * 
     * 
     * @param id 
     */
    findUtilisateurById(id: number, extraHttpRequestParams?: any): Observable<UtilisateurResponse>;

    /**
     * 
     * 
     * @param login 
     */
    findUtilisateurByLogin(login: string, extraHttpRequestParams?: any): Observable<UtilisateurResponse>;

    /**
     * 
     * 
     * @param utilisateurRequest 
     */
    saveUtilisateur(utilisateurRequest: UtilisateurRequest, extraHttpRequestParams?: any): Observable<number>;

    /**
     * 
     * 
     * @param id 
     * @param file 
     */
    uploadUtilisateurPhoto(id: number, file: Blob, extraHttpRequestParams?: any): Observable<object>;

}
