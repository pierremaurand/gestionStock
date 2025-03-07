import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-ligne-commande-client-fournisseur',
  imports: [],
  templateUrl: './ligne-commande-client-fournisseur.component.html',
  styleUrl: './ligne-commande-client-fournisseur.component.scss'
})
export class LigneCommandeClientFournisseurComponent {
  @Input()
  ligneCommande: any = {};

  get prixTotal(): number {
    if(this.ligneCommande.quantite && this.ligneCommande.prixUnitaire) {
      return +this.ligneCommande.quantite * +this.ligneCommande.prixUnitaire ;
    }
    return 0;
  }
}
