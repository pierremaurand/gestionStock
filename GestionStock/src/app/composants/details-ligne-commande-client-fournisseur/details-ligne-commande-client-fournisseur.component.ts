import { Component, Input } from '@angular/core';
import { LigneCommande } from '../nouvelle-commande-client-fournisseur/ligneCommande';

@Component({
  selector: 'app-details-ligne-commande-client-fournisseur',
  imports: [],
  templateUrl: './details-ligne-commande-client-fournisseur.component.html',
  styleUrl: './details-ligne-commande-client-fournisseur.component.scss'
})
export class DetailsLigneCommandeClientFournisseurComponent {
  @Input()
  ligneCommande: LigneCommande = {};

  get prixTotal(): number {
    if(this.ligneCommande.quantite && this.ligneCommande.prixUnitaire) {
      return +this.ligneCommande.quantite * +this.ligneCommande.prixUnitaire ;
    }
    return 0;
  }
}
