/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { findAll } from '../fn/vente/find-all';
import { FindAll$Params } from '../fn/vente/find-all';
import { findAllLigneVente } from '../fn/vente/find-all-ligne-vente';
import { FindAllLigneVente$Params } from '../fn/vente/find-all-ligne-vente';
import { findAllLigneVenteByArticle } from '../fn/vente/find-all-ligne-vente-by-article';
import { FindAllLigneVenteByArticle$Params } from '../fn/vente/find-all-ligne-vente-by-article';
import { findAllLigneVenteByVente } from '../fn/vente/find-all-ligne-vente-by-vente';
import { FindAllLigneVenteByVente$Params } from '../fn/vente/find-all-ligne-vente-by-vente';
import { PageResponseLigneVenteResponse } from '../models/page-response-ligne-vente-response';
import { PageResponseVenteResponse } from '../models/page-response-vente-response';
import { save } from '../fn/vente/save';
import { Save$Params } from '../fn/vente/save';

@Injectable({ providedIn: 'root' })
export class VenteService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAll()` */
  static readonly FindAllPath = '/ventes';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAll()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAll$Response(params?: FindAll$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseVenteResponse>> {
    return findAll(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAll$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAll(params?: FindAll$Params, context?: HttpContext): Observable<PageResponseVenteResponse> {
    return this.findAll$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseVenteResponse>): PageResponseVenteResponse => r.body)
    );
  }

  /** Path part for operation `save()` */
  static readonly SavePath = '/ventes';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `save()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  save$Response(params: Save$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return save(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `save$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  save(params: Save$Params, context?: HttpContext): Observable<number> {
    return this.save$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findAllLigneVente()` */
  static readonly FindAllLigneVentePath = '/ventes/lignes-vente';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllLigneVente()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllLigneVente$Response(params?: FindAllLigneVente$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseLigneVenteResponse>> {
    return findAllLigneVente(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllLigneVente$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllLigneVente(params?: FindAllLigneVente$Params, context?: HttpContext): Observable<PageResponseLigneVenteResponse> {
    return this.findAllLigneVente$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseLigneVenteResponse>): PageResponseLigneVenteResponse => r.body)
    );
  }

  /** Path part for operation `findAllLigneVenteByVente()` */
  static readonly FindAllLigneVenteByVentePath = '/ventes/lignes-vente/vente/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllLigneVenteByVente()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllLigneVenteByVente$Response(params: FindAllLigneVenteByVente$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseLigneVenteResponse>> {
    return findAllLigneVenteByVente(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllLigneVenteByVente$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllLigneVenteByVente(params: FindAllLigneVenteByVente$Params, context?: HttpContext): Observable<PageResponseLigneVenteResponse> {
    return this.findAllLigneVenteByVente$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseLigneVenteResponse>): PageResponseLigneVenteResponse => r.body)
    );
  }

  /** Path part for operation `findAllLigneVenteByArticle()` */
  static readonly FindAllLigneVenteByArticlePath = '/ventes/lignes-vente/article/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllLigneVenteByArticle()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllLigneVenteByArticle$Response(params: FindAllLigneVenteByArticle$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseLigneVenteResponse>> {
    return findAllLigneVenteByArticle(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllLigneVenteByArticle$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllLigneVenteByArticle(params: FindAllLigneVenteByArticle$Params, context?: HttpContext): Observable<PageResponseLigneVenteResponse> {
    return this.findAllLigneVenteByArticle$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseLigneVenteResponse>): PageResponseLigneVenteResponse => r.body)
    );
  }

}
