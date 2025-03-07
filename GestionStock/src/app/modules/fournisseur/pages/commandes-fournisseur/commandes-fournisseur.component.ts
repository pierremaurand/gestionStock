import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { CommonModule } from '@angular/common';
import { LigneCommandeFournisseurComponent } from '../../composants/ligne-commande-fournisseur/ligne-commande-fournisseur.component';
import { PageResponseCommandeFournisseurResponse, CommandeFournisseurService, CommandeFournisseurResponse, FournisseurResponse, FournisseurService } from '../../../../services/openapi';
import { ToastService } from '../../../../services/toast/toast.service';

@Component({
  selector: 'app-commandes-fournisseur',
  imports: [CommonModule, PaginationComponent, BouttonsActionComponent, LigneCommandeFournisseurComponent],
  templateUrl: './commandes-fournisseur.component.html',
  styleUrl: './commandes-fournisseur.component.scss'
})
export class CommandesFournisseurComponent implements OnInit{
  commandeResponse: PageResponseCommandeFournisseurResponse = {};
  fournisseurs: Array<FournisseurResponse> = [];
  page = 0;
  size = 10;
  pages: any = [];

  constructor(
    private router: Router,
    private commandeFournisseurService: CommandeFournisseurService,
    private fournisseurService: FournisseurService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.findAllCommandeFournisseurs();
  }

  nouvelleCommande(): void {
    this.router.navigate(['home', 'fournisseurs', 'nouvelle-commande']);
  }

  private findAllCommandeFournisseurs() {
    this.fournisseurService.findAllFournisseursList().subscribe({
      next: (fournisseurs) => {
        this.fournisseurs = fournisseurs;
        this.commandeFournisseurService.findAllCommandeFournisseurs(this.page, this.size).subscribe({
          next: (commandes) => {
            this.commandeResponse = commandes;
            this.pages = Array(this.commandeResponse.totalPages)
              .fill(0)
              .map((x, i) => i);
          },
        });
      }
    });

  }

  goToPage(page: number) {
    this.page = page;
    this.findAllCommandeFournisseurs();
  }

  edit(commande: CommandeFournisseurResponse) {
    this.router.navigate(['home', 'fournisseurs', 'nouvelle-commande', commande.id]);
  }

  delete(commande: CommandeFournisseurResponse) {
    this.commandeFournisseurService.deleteCommandeFournisseur(commande.id as number).subscribe({
      next: () => {
        this.toastService.showSuccess("Commande fournisseur supprimée aves succès");
        this.findAllCommandeFournisseurs();
      },
      error: (err) => {
        this.toastService.showError(err.error.error);
      },
    });
  }

  details(commande: CommandeFournisseurResponse) {}

}
