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

import { CategorieRequest } from '../model/models';
import { CategorieResponse } from '../model/models';
import { PageResponseCategorieResponse } from '../model/models';


import { Configuration }                                     from '../configuration';



export interface CategorieServiceInterface {
    defaultHeaders: HttpHeaders;
    configuration: Configuration;

    /**
     * 
     * 
     * @param id 
     */
    deleteCategorie(id: number, extraHttpRequestParams?: any): Observable<object>;

    /**
     * 
     * 
     * @param page 
     * @param size 
     */
    findAllCategories(page?: number, size?: number, extraHttpRequestParams?: any): Observable<PageResponseCategorieResponse>;

    /**
     * 
     * 
     * @param id 
     */
    findCategorieById(id: number, extraHttpRequestParams?: any): Observable<CategorieResponse>;

    /**
     * 
     * 
     * @param code 
     */
    findCategorieById1(code: string, extraHttpRequestParams?: any): Observable<CategorieResponse>;

    /**
     * 
     * 
     */
    findCategoriesList(extraHttpRequestParams?: any): Observable<Array<CategorieResponse>>;

    /**
     * 
     * 
     * @param categorieRequest 
     */
    saveCategorie(categorieRequest: CategorieRequest, extraHttpRequestParams?: any): Observable<number>;

}
