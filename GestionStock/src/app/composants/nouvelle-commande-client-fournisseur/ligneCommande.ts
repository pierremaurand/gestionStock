import { ArticleResponse } from "../../services/openapi";

export interface LigneCommande {
  id?: number;
  article?: ArticleResponse;
  quantite?: number;
  prixUnitaire?: number;
}
