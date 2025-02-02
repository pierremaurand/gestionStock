/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseCommandeFournisseurResponse } from '../../models/page-response-commande-fournisseur-response';

export interface FindAll5$Params {
  page?: number;
  size?: number;
}

export function findAll5(http: HttpClient, rootUrl: string, params?: FindAll5$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseCommandeFournisseurResponse>> {
  const rb = new RequestBuilder(rootUrl, findAll5.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseCommandeFournisseurResponse>;
    })
  );
}

findAll5.PATH = '/commandes-fournisseur';
