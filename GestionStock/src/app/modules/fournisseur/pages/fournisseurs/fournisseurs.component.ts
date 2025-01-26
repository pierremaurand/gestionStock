import { Component } from '@angular/core';
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { DetailsClientFournisseurComponent } from "../../../../composants/details-client-fournisseur/details-client-fournisseur.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-fournisseurs',
  imports: [PaginationComponent, BouttonsActionComponent, DetailsClientFournisseurComponent],
  templateUrl: './fournisseurs.component.html',
  styleUrl: './fournisseurs.component.scss'
})
export class FournisseursComponent {

  constructor(private router: Router){}

  nouveauFournisseur(): void{
    this.router.navigate(['home/fournisseurs/nouveau-fournisseur']);
  }

}
