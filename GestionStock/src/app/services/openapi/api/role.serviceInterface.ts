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

import { PageResponseRoleResponse } from '../model/models';
import { RoleRequest } from '../model/models';
import { RoleResponse } from '../model/models';


import { Configuration }                                     from '../configuration';



export interface RoleServiceInterface {
    defaultHeaders: HttpHeaders;
    configuration: Configuration;

    /**
     * 
     * 
     * @param page 
     * @param size 
     */
    findAllRoles(page?: number, size?: number, extraHttpRequestParams?: any): Observable<PageResponseRoleResponse>;

    /**
     * 
     * 
     * @param roleId 
     */
    findRoleById(roleId: number, extraHttpRequestParams?: any): Observable<RoleResponse>;

    /**
     * 
     * 
     * @param roleName 
     */
    findRoleByName(roleName: string, extraHttpRequestParams?: any): Observable<RoleResponse>;

    /**
     * 
     * 
     * @param roleRequest 
     */
    saveRole(roleRequest: RoleRequest, extraHttpRequestParams?: any): Observable<number>;

}
