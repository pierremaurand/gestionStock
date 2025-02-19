import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BouttonsActionComponent } from '../../../../composants/bouttons-action/bouttons-action.component';
import { PaginationComponent } from '../../../../composants/pagination/pagination.component';
import { Router } from '@angular/router';
import {
  CategorieResponse,
  CategorieService,
  PageResponseCategorieResponse,
} from '../../../../services/openapi';
import { LigneCategorieComponent } from '../../composants/ligne-categorie/ligne-categorie.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-categories',
  imports: [
    CommonModule,
    BouttonsActionComponent,
    PaginationComponent,
    LigneCategorieComponent,
  ],
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.scss',
})
export class CategoriesComponent implements OnInit {
  categorieResponse: PageResponseCategorieResponse = {};
  page = 0;
  size = 10;
  pages: any = [];

  constructor(
    private router: Router,
    private categorieService: CategorieService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.findAllCategories();
  }

  private findAllCategories() {
    this.categorieService.findAllCategories(this.page, this.size).subscribe({
      next: (categories) => {
        console.log(categories);
        this.categorieResponse = categories;
        this.pages = Array(this.categorieResponse.totalPages)
          .fill(0)
          .map((x, i) => i);
      },
    });
  }

  nouvelleCategorie(): void {
    this.router.navigate(['home', 'parametres', 'nouvelle-categorie']);
  }

  goToPage(page: number) {
    this.page = page;
    this.findAllCategories();
  }

  onEdit(categorie: CategorieResponse) {
    this.router.navigate([
      'home',
      'parametres',
      'nouvelle-categorie',
      categorie.id,
    ]);
  }

  onDetails(categorie: CategorieResponse) {}

  onDelete(categorie: CategorieResponse) {
    this.categorieService.deleteCategorie(categorie.id as number).subscribe({
      next: () => {
        this.toastr.success("Catégorie supprimer avec success","Success");
        this.findAllCategories();
      },
      error: (err) => {
        console.log(err);
        this.toastr.error(err.error.error, "Error");
      },
    });
  }
}
