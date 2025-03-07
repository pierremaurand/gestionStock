import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { DetailsLigneCommandeClientFournisseurComponent } from "../details-ligne-commande-client-fournisseur/details-ligne-commande-client-fournisseur.component";

@Component({
  selector: 'app-details-commande-client-fournisseur',
  imports: [CommonModule,DetailsLigneCommandeClientFournisseurComponent],
  templateUrl: './details-commande-client-fournisseur.component.html',
  styleUrl: './details-commande-client-fournisseur.component.scss'
})
export class DetailsCommandeClientFournisseurComponent {
  @Input()
  commande: any = {};
}
