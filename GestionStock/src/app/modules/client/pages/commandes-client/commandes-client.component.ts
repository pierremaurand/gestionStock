import { Component } from '@angular/core';
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { DetailsCommandeClientFournisseurComponent } from "../../../../composants/details-commande-client-fournisseur/details-commande-client-fournisseur.component";
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-commandes-client',
  imports: [BouttonsActionComponent, DetailsCommandeClientFournisseurComponent, PaginationComponent],
  templateUrl: './commandes-client.component.html',
  styleUrl: './commandes-client.component.scss'
})
export class CommandesClientComponent {

  constructor(private router: Router){}

  nouvelleCommande(): void{
    this.router.navigate(['home/clients/nouvelle-commande']);
  }

}
