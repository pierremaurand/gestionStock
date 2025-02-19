import { ToastService } from './../../../../services/toast/toast.service';
import { Component, OnInit } from '@angular/core';
import { BouttonsActionComponent } from '../../../../composants/bouttons-action/bouttons-action.component';
import { DetailsCommandeClientFournisseurComponent } from '../../../../composants/details-commande-client-fournisseur/details-commande-client-fournisseur.component';
import { PaginationComponent } from '../../../../composants/pagination/pagination.component';
import { Router } from '@angular/router';
import {
  ClientResponse,
  ClientService,
  CommandeClientResponse,
  CommandeClientService,
  PageResponseCommandeClientResponse,
} from '../../../../services/openapi';

@Component({
  selector: 'app-commandes-client',
  imports: [
    BouttonsActionComponent,
    DetailsCommandeClientFournisseurComponent,
    PaginationComponent,
  ],
  templateUrl: './commandes-client.component.html',
  styleUrl: './commandes-client.component.scss',
})
export class CommandesClientComponent implements OnInit{
  commandeResponse: PageResponseCommandeClientResponse = {};
  clients: Array<ClientResponse> = [];
  page = 0;
  size = 10;
  pages: any = [];

  constructor(
    private router: Router,
    private commandeClientService: CommandeClientService,
    private clientService: ClientService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.findAllCommandeClients();
  }

  nouvelleCommande(): void {
    this.router.navigate(['home', 'clients', 'nouvelle-commande']);
  }

  private findAllCommandeClients() {
    this.clientService.findAllClientsList().subscribe({
      next: (clients) => {
        this.clients = clients;
        this.commandeClientService.findAllCommandeClients(this.page, this.size).subscribe({
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
    this.findAllCommandeClients();
  }

  edit(commande: CommandeClientResponse) {
    this.router.navigate(['home', 'clients', 'nouvelle-commande', commande.id]);
  }

  delete(commande: CommandeClientResponse) {
    this.commandeClientService.deleteCommandeClient(commande.id as number).subscribe({
      next: () => {
        this.toastService.showSuccess("Commande client supprimée aves succès");
        this.findAllCommandeClients();
      },
      error: (err) => {
        this.toastService.showError(err.error.error);
      },
    });
  }

  details(commande: CommandeClientResponse) {}
}
