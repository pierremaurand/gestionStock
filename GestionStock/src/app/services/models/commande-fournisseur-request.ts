/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { LigneCommandeFournisseurRequest } from '../models/ligne-commande-fournisseur-request';
export interface CommandeFournisseurRequest {
  code: string;
  dateCommande: string;
  etatCommande: 'EN_PREPARATION' | 'VALIDEE' | 'LIVREE';
  fournisseur: number;
  ligneCommandeFournisseurs: Array<LigneCommandeFournisseurRequest>;
}
