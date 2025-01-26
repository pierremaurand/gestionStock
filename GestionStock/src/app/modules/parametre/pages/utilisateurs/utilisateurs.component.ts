import { Component } from '@angular/core';
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { Router } from '@angular/router';
import { DetailsUtilisateurComponent } from "../../composants/details-utilisateur/details-utilisateur.component";

@Component({
  selector: 'app-utilisateurs',
  imports: [BouttonsActionComponent, PaginationComponent, DetailsUtilisateurComponent],
  templateUrl: './utilisateurs.component.html',
  styleUrl: './utilisateurs.component.scss'
})
export class UtilisateursComponent {

  constructor(private router: Router){}

  nouveauUtilisateur(): void {
    this.router.navigate(['home/parametres/nouveau-utilisateur']);
  }

}
