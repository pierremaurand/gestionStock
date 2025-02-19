import { CommonModule } from '@angular/common';
import { CategorieRequest } from './../../../../services/openapi/model/categorie-request';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategorieService } from '../../../../services/openapi';
import { FormsModule } from '@angular/forms';
import { ToastService } from '../../../../services/toast/toast.service';

@Component({
  selector: 'app-nouvelle-categorie',
  imports: [CommonModule, FormsModule],
  templateUrl: './nouvelle-categorie.component.html',
  styleUrl: './nouvelle-categorie.component.scss',
})
export class NouvelleCategorieComponent implements OnInit {
  categorieRequest: CategorieRequest = { code: '', designation: '' };

  constructor(
    private router: Router,
    private categorieService: CategorieService,
    private activatedRoute: ActivatedRoute,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    const categorieId = this.activatedRoute.snapshot.params['categorieId'];
    if (categorieId) {
      this.categorieService.findCategorieById(categorieId).subscribe({
        next: (categorie) => {
          this.categorieRequest = {
            id: categorie.id,
            code: categorie.code as string,
            designation: categorie.designation as string,
          };
        },
      });
    } else {
      this.categorieRequest = {
        code: this.generateCode(),
        designation: ""
      }
    }
  }


  generateCode(): string {
    return "CAT"+Date.now();
  }
  onCancel(): void {
    this.router.navigate(['home/parametres/categories']);
  }

  save(): void {
    this.categorieService.saveCategorie(this.categorieRequest).subscribe({
      next: () => {
        this.toastService.saveSuccess("Catégorie", this.categorieRequest.id);
        this.onCancel();
      },
      error: (err) => {
        console.log(err.error);

        if (err.error.validationErrors) {
          err.error.validationErrors.forEach((message: string) => {
            this.toastService.showWarning(message);
          });
        } else {
          this.toastService.showError(err.error.error);
        }
      },
    });
  }
}
