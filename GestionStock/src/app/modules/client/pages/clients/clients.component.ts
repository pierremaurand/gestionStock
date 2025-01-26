import { Component } from '@angular/core';
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { DetailsClientFournisseurComponent } from '../../../../composants/details-client-fournisseur/details-client-fournisseur.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clients',
  imports: [PaginationComponent, BouttonsActionComponent, DetailsClientFournisseurComponent],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.scss'
})
export class ClientsComponent {

  constructor(private router: Router){}

  nouveauClient(): void{
    this.router.navigate(['home/clients/nouveau-client']);
  }
}
