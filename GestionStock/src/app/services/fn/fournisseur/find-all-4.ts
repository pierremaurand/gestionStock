/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseFournisseurResponse } from '../../models/page-response-fournisseur-response';

export interface FindAll4$Params {
  page?: number;
  size?: number;
}

export function findAll4(http: HttpClient, rootUrl: string, params?: FindAll4$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFournisseurResponse>> {
  const rb = new RequestBuilder(rootUrl, findAll4.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseFournisseurResponse>;
    })
  );
}

findAll4.PATH = '/fournisseurs';
