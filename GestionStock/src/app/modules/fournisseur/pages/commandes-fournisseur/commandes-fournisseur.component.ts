import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { DetailsCommandeClientFournisseurComponent } from "../../../../composants/details-commande-client-fournisseur/details-commande-client-fournisseur.component";
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";

@Component({
  selector: 'app-commandes-fournisseur',
  imports: [DetailsCommandeClientFournisseurComponent, PaginationComponent, BouttonsActionComponent],
  templateUrl: './commandes-fournisseur.component.html',
  styleUrl: './commandes-fournisseur.component.scss'
})
export class CommandesFournisseurComponent {

  constructor(private router: Router){}

  nouvelleCommande():void{
    this.router.navigate(['home/fournisseurs/nouvelle-commande']);
  }

}
