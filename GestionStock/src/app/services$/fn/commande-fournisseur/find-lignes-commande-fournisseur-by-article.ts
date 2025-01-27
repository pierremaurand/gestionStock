/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseLigneCommandeFournisseurResponse } from '../../models/page-response-ligne-commande-fournisseur-response';

export interface FindLignesCommandeFournisseurByArticle$Params {
  page?: number;
  size?: number;
  id: number;
}

export function findLignesCommandeFournisseurByArticle(http: HttpClient, rootUrl: string, params: FindLignesCommandeFournisseurByArticle$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseLigneCommandeFournisseurResponse>> {
  const rb = new RequestBuilder(rootUrl, findLignesCommandeFournisseurByArticle.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseLigneCommandeFournisseurResponse>;
    })
  );
}

findLignesCommandeFournisseurByArticle.PATH = '/commandes-fournisseur/lignes-commande/article/{id}';
