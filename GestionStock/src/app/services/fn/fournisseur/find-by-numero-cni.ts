/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { FournisseurResponse } from '../../models/fournisseur-response';

export interface FindByNumeroCni$Params {
  'numero-cni': string;
}

export function findByNumeroCni(http: HttpClient, rootUrl: string, params: FindByNumeroCni$Params, context?: HttpContext): Observable<StrictHttpResponse<FournisseurResponse>> {
  const rb = new RequestBuilder(rootUrl, findByNumeroCni.PATH, 'get');
  if (params) {
    rb.path('numero-cni', params['numero-cni'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<FournisseurResponse>;
    })
  );
}

findByNumeroCni.PATH = '/fournisseurs/numero-cni/{numero-cni}';
