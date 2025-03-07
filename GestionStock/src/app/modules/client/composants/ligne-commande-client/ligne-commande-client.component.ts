import { Component, Input } from '@angular/core';
import { CommandeClientResponse } from '../../../../services/openapi';
import { CommonModule } from '@angular/common';
import { LigneCommandeClientFournisseurComponent } from '../../../../composants/ligne-commande-client-fournisseur/ligne-commande-client-fournisseur.component';

@Component({
  selector: 'app-ligne-commande-client',
  imports: [CommonModule, LigneCommandeClientFournisseurComponent],
  templateUrl: './ligne-commande-client.component.html',
  styleUrl: './ligne-commande-client.component.scss'
})
export class LigneCommandeClientComponent {
  @Input()
  commande: CommandeClientResponse = {};

  get nbrLignes(): number {
    return this.commande.ligneCommandeClients?.length??0;
  }

  get totalCommande(): number {
    let montant = 0;
    this.commande.ligneCommandeClients?.forEach(ligne => {
      if(ligne.quantite && ligne.prixUnitaire) {
        montant += ligne.quantite * ligne.prixUnitaire;
      }
    });
    return montant;
  }

  get clientPhoto(): string | undefined {
    if(this.commande.client?.photo) {
      return 'data:image/jpg;base64,'+ this.commande.client?.photo;
    }
    return 'assets/images/profil.png';
  }

}
