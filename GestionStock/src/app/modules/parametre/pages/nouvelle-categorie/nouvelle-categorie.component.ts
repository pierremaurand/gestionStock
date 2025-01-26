import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nouvelle-categorie',
  imports: [],
  templateUrl: './nouvelle-categorie.component.html',
  styleUrl: './nouvelle-categorie.component.scss'
})
export class NouvelleCategorieComponent {

  constructor(private router: Router){}

  onCancel(): void {
    this.router.navigate(['home/parametres/categories']);
  }

}
