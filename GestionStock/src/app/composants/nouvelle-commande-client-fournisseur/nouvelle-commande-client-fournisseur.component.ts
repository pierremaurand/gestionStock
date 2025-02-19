import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { DetailsLigneCommandeClientFournisseurComponent } from '../details-ligne-commande-client-fournisseur/details-ligne-commande-client-fournisseur.component';
import { ActivatedRoute, Router } from '@angular/router';
import {
  ArticleResponse,
  ArticleService,
  ClientResponse,
  ClientService,
  CommandeClientService,
  CommandeFournisseurService,
  FournisseurResponse,
  FournisseurService,
} from '../../services/openapi';

@Component({
  selector: 'app-nouvelle-commande-client-fournisseur',
  imports: [CommonModule, FormsModule, DetailsLigneCommandeClientFournisseurComponent],
  templateUrl: './nouvelle-commande-client-fournisseur.component.html',
  styleUrl: './nouvelle-commande-client-fournisseur.component.scss',
})
export class NouvelleCommandeClientFournisseurComponent implements OnInit {
  commandeRequest: any = {};
  selectedClientFournisseur: any = {};
  clientsFournisseurs: Array<any> = [];
  articles: Array<ArticleResponse> = []
  origin = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private articleService: ArticleService,
    private clientService: ClientService,
    private commandeClientService: CommandeClientService,
    private fournisseurService: FournisseurService,
    private commandeFournisseurService: CommandeFournisseurService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe((data) => {
      this.origin = data['origin'];
      const commandeId =
        this.activatedRoute.snapshot.params['commandeId'];
      if (commandeId) {
        this.getCommandeById(commandeId);
      }
      this.findAllArticles();
      this.findAll();
    });
  }

  findAll() {
    if(this.origin == 'client') {
      this.findAllClients();
    } else {
      this.findAllFournisseurs();
    }
  }

  getCommandeById(id: number) {
    if (this.origin == 'client') {
      this.getCommandeClientById(id);
    } else if(this.origin == 'fournisseur') {
      this.getCommandeFournisseurById(id);
    }
  }

  findAllArticles() {

  }

  findAllClients() {
    this.clientService.findAllClientsList().subscribe({
      next: (clients) => {
        this.clientsFournisseurs = clients as Array<ClientResponse>;
      }
    });
  }

  findAllFournisseurs() {
    this.fournisseurService.findAllFournisseursList().subscribe({
      next: (fournisseurs) => {
        this.clientsFournisseurs = fournisseurs as Array<FournisseurResponse>;
      }
    });
  }

  getCommandeClientById(id: number) {
    this.commandeClientService.findCommandeClientById(id).subscribe({
      next: (commande) => {

      }
    });
  }

  getCommandeFournisseurById(id: number) {
     this.commandeFournisseurService.findCommandeFournisseurById(id).subscribe({
      next: (commande) => {

      }
    });
  }

  onCancel(): void {
    if (this.origin == 'client') {
      this.router.navigate(['home','clients','commandes-client']);
    } else {
      this.router.navigate(['home','fournisseurs','commandes-fournisseur']);
    }
  }

  get getClientFournisseurPhoto(): string | undefined {
    if(this.selectedClientFournisseur.photo) {
      return 'data:image/jpg;base64,'+ this.selectedClientFournisseur.photo;
    }
    return 'assets/images/profil.png';
  }


}
