import { Component, OnInit } from '@angular/core';
import { PaginationComponent } from "../../../../composants/pagination/pagination.component";
import { BouttonsActionComponent } from "../../../../composants/bouttons-action/bouttons-action.component";
import { Router } from '@angular/router';
import { PageResponseClientResponse, ClientService, ClientResponse } from '../../../../services/openapi';
import { LigneClientComponent } from '../../composants/ligne-client/ligne-client.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-clients',
  imports: [CommonModule, PaginationComponent, BouttonsActionComponent, LigneClientComponent],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.scss'
})
export class ClientsComponent implements OnInit{
  clientResponse: PageResponseClientResponse = {};
  page = 0;
  size = 10;
  pages: any = [];

  constructor(
    private router: Router,
    private clientService: ClientService
  ) {}

  ngOnInit(): void {
    this.findAllClients();
  }

  private findAllClients() {
    this.clientService.findAllClients(this.page, this.size).subscribe({
      next: (clients) => {
        console.log(clients);
        this.clientResponse = clients;
        this.pages = Array(this.clientResponse.totalPages)
          .fill(0)
          .map((x, i) => i);
      },
    });
  }

  nouveauClient(): void {
    this.router.navigate(['home','clients','nouveau-client']);
  }

  goToPage(page: number) {
    this.page = page;
    this.findAllClients();
  }

  onEdit(client: ClientResponse) {
    this.router.navigate([
      'home',
      'clients',
      'nouveau-client',
      client.id,
    ]);
  }

  onDetails(client: ClientResponse) {}

  onDelete(client: ClientResponse) {
    // this.clientService.dele(client.id as number).subscribe({
    //   next: () => {
    //     this.toastr.success("Catégorie supprimer avec success","Success");
    //     this.findAllClients();
    //   },
    //   error: (err) => {
    //     console.log(err);
    //     this.toastr.error(err.error.error, "Error");
    //   },
    // });
  }

}
