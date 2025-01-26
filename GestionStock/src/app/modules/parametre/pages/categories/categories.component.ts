import { Component } from '@angular/core';
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-categories',
  imports: [BouttonsActionComponent, PaginationComponent],
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.scss'
})
export class CategoriesComponent {

  constructor(private router: Router) {

  }

  nouvelleCategorie(): void {
    this.router.navigate(['home/parametres/nouvelle-categorie']);
  }
}
