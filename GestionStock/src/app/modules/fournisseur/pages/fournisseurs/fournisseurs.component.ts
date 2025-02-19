import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { Router } from '@angular/router';
import { PageResponseFournisseurResponse, FournisseurService, FournisseurResponse } from '../../../../services/openapi';
import { LigneFournisseurComponent } from '../../composants/ligne-fournisseur/ligne-fournisseur.component';

@Component({
  selector: 'app-fournisseurs',
  imports: [CommonModule, PaginationComponent, BouttonsActionComponent, LigneFournisseurComponent],
  templateUrl: './fournisseurs.component.html',
  styleUrl: './fournisseurs.component.scss'
})
export class FournisseursComponent implements OnInit{
  fournisseurResponse: PageResponseFournisseurResponse = {};
  page = 0;
  size = 10;
  pages: any = [];

  constructor(
    private router: Router,
    private fournisseurService: FournisseurService
  ) {}

  ngOnInit(): void {
    this.findAllFournisseurs();
  }

  private findAllFournisseurs() {
    this.fournisseurService.findAllFournisseurs(this.page, this.size).subscribe({
      next: (fournisseurs) => {
        console.log(fournisseurs);
        this.fournisseurResponse = fournisseurs;
        this.pages = Array(this.fournisseurResponse.totalPages)
          .fill(0)
          .map((x, i) => i);
      },
    });
  }

  nouveauFournisseur(): void {
    this.router.navigate(['home','fournisseurs','nouveau-fournisseur']);
  }

  goToPage(page: number) {
    this.page = page;
    this.findAllFournisseurs();
  }

  onEdit(fournisseur: FournisseurResponse) {
    this.router.navigate([
      'home',
      'fournisseurs',
      'nouveau-fournisseur',
      fournisseur.id,
    ]);
  }

  onDetails(fournisseur: FournisseurResponse) {}

  onDelete(fournisseur: FournisseurResponse) {
    // this.fournisseurService.dele(fournisseur.id as number).subscribe({
    //   next: () => {
    //     this.toastr.success("Catégorie supprimer avec success","Success");
    //     this.findAllFournisseurs();
    //   },
    //   error: (err) => {
    //     console.log(err);
    //     this.toastr.error(err.error.error, "Error");
    //   },
    // });
  }

}
