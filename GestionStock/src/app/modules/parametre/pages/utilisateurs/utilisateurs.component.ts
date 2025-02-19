import { Component, OnInit } from '@angular/core';
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { Router } from '@angular/router';
import { PageResponseUtilisateurResponse, UtilisateurResponse, UtilisateurService } from '../../../../services/openapi';
import { ToastrService } from 'ngx-toastr';
import { LigneUtilisateurComponent } from '../../composants/ligne-utilisateur/ligne-utilisateur.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-utilisateurs',
  imports: [CommonModule,BouttonsActionComponent, PaginationComponent, LigneUtilisateurComponent],
  templateUrl: './utilisateurs.component.html',
  styleUrl: './utilisateurs.component.scss'
})
export class UtilisateursComponent implements OnInit{
  utilisateurResponse: PageResponseUtilisateurResponse = {};
  page = 0;
  size = 10;
  pages: any = [];

  constructor(
    private router: Router,
    private utilisateurService: UtilisateurService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.findAllUtilisateurs();
  }

  private findAllUtilisateurs() {
    this.utilisateurService.findAllUtilisateurs(this.page, this.size).subscribe({
      next: (utilisateurs) => {
        console.log(utilisateurs);
        this.utilisateurResponse = utilisateurs;
        this.pages = Array(this.utilisateurResponse.totalPages)
          .fill(0)
          .map((x, i) => i);
      },
    });
  }

  nouveauUtilisateur(): void {
    this.router.navigate(['home','parametres','nouveau-utilisateur']);
  }

  goToPage(page: number) {
    this.page = page;
    this.findAllUtilisateurs();
  }

  onEdit(utilisateur: UtilisateurResponse) {
    this.router.navigate([
      'home',
      'parametres',
      'nouveau-utilisateur',
      utilisateur.id,
    ]);
  }

  onDetails(utilisateur: UtilisateurResponse) {}

  onDelete(utilisateur: UtilisateurResponse) {
    // this.utilisateurService.dele(utilisateur.id as number).subscribe({
    //   next: () => {
    //     this.toastr.success("Catégorie supprimer avec success","Success");
    //     this.findAllUtilisateurs();
    //   },
    //   error: (err) => {
    //     console.log(err);
    //     this.toastr.error(err.error.error, "Error");
    //   },
    // });
  }

}
