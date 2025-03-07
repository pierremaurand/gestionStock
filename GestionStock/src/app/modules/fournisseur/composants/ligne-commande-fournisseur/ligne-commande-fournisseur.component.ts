import { Component, Input } from '@angular/core';
import { CommandeFournisseurResponse } from '../../../../services/openapi/model/models';
import { CommonModule } from '@angular/common';
import { LigneCommandeClientFournisseurComponent } from '../../../../composants/ligne-commande-client-fournisseur/ligne-commande-client-fournisseur.component';

@Component({
  selector: 'app-ligne-commande-fournisseur',
  imports: [CommonModule, LigneCommandeClientFournisseurComponent],
  templateUrl: './ligne-commande-fournisseur.component.html',
  styleUrl: './ligne-commande-fournisseur.component.scss'
})
export class LigneCommandeFournisseurComponent {
  @Input()
  commande: CommandeFournisseurResponse = {};

  get nbrLignes(): number {
    return this.commande.ligneCommandeFournisseurs?.length??0;
  }

  get totalCommande(): number {
    let montant = 0;
    this.commande.ligneCommandeFournisseurs?.forEach(ligne => {
      if(ligne.quantite && ligne.prixUnitaire) {
        montant += ligne.quantite * ligne.prixUnitaire;
      }
    });
    return montant;
  }

  get fournisseurPhoto(): string | undefined {
    if(this.commande.fournisseur?.photo) {
      return 'data:image/jpg;base64,'+ this.commande.fournisseur?.photo;
    }
    return 'assets/images/profil.png';
  }
}
