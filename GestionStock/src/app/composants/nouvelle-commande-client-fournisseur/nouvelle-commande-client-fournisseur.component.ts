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
  CommandeClientRequest,
  CommandeClientService,
  CommandeFournisseurRequest,
  CommandeFournisseurService,
  FournisseurResponse,
  FournisseurService,
} from '../../services/openapi';
import { AutocompleteComponent } from '../autocomplete/autocomplete.component';
import { LigneCommande } from './ligneCommande';
import { ToastService } from '../../services/toast/toast.service';

@Component({
  selector: 'app-nouvelle-commande-client-fournisseur',
  imports: [
    CommonModule,
    FormsModule,
    DetailsLigneCommandeClientFournisseurComponent,
    AutocompleteComponent,
  ],
  templateUrl: './nouvelle-commande-client-fournisseur.component.html',
  styleUrl: './nouvelle-commande-client-fournisseur.component.scss',
})
export class NouvelleCommandeClientFournisseurComponent implements OnInit {
  commandeRequest: any = {};
  selectedClientFournisseur: any = {};
  clientsFournisseurs: Array<any> = [];
  articles: Array<ArticleResponse> = [];
  ligneCommandes: Array<LigneCommande> = [];
  ligneCommande: LigneCommande = {};
  selectedArticle: any = {};
  origin = '';
  quantite: any = '';
  prixUnitaire: any = '';
  totalCommande: any = '';

  clientLabel: string = 'Selectionner un client';
  articleLabel: string = 'Selectionner un article';

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private articleService: ArticleService,
    private clientService: ClientService,
    private commandeClientService: CommandeClientService,
    private fournisseurService: FournisseurService,
    private commandeFournisseurService: CommandeFournisseurService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe((data) => {
      this.origin = data['origin'];
      const commandeId = this.activatedRoute.snapshot.params['commandeId'];
      if (commandeId) {
        this.getCommandeById(commandeId);
      } else {
        const today = new Date();
        this.commandeRequest = {
          code: this.generateCode(),
          dateCommande: today.toISOString().substring(0,10),
          etatCommande: 'EN_PREPARATION'
        };
      }
      this.findAllArticles();
      this.findAll();
    });
  }

  private generateCode(): string {
    if (this.origin == 'client') {
      return 'CMDCLT' + Date.now();
    }
    return 'CMDFRS' + Date.now();
  }

  private checkLigneCommande(): void {
    if (this.quantite && this.selectedArticle && this.prixUnitaire) {
      const ligneCommandeExists = this.ligneCommandes.find(ligne => ligne.article?.code === this.selectedArticle.code);
      if(ligneCommandeExists) {
        this.ligneCommandes.forEach(ligne => {
          if(ligne && ligne.article?.code === this.selectedArticle.code) {
            ligne.quantite = ligne.quantite+this.quantite;
          }
        });
      } else {
        this.ligneCommande = {
          article: this.selectedArticle,
          quantite: this.quantite,
          prixUnitaire: this.prixUnitaire,
        };
        this.ligneCommandes.push(this.ligneCommande);
      }
      this.quantite = '';
      this.prixUnitaire = '';
      this.selectedArticle = {};
    } else {
      this.toastService.showWarning("La quantité ou l'article n'a pas été renseignée");
    }
  }

  private prepareCommande(): void {
    if(this.origin === 'client') {
      this.commandeRequest.client = this.selectedClientFournisseur.id;
      this.commandeRequest.ligneCommandeClients = this.prepareLigneCommandes();
    } else {
      this.commandeRequest.fournisseur = this.selectedClientFournisseur.id;
      this.commandeRequest.ligneCommandeFournisseurs = this.prepareLigneCommandes();
    }
  }

  private prepareLigneCommandes(): Array<any> {
    const ligneCommandes: Array<any> = [];
    this.ligneCommandes.forEach(ligne => {
      ligneCommandes.push({
        id: ligne.id,
        article: ligne.article?.id,
        quantite: ligne.quantite,
        prixUnitaire: ligne.prixUnitaire
      });
    });
    return ligneCommandes;
  }

  private calculTotalCommande(): void {
    this.totalCommande = 0;
    this.ligneCommandes.forEach(ligne => {
      if(ligne.quantite && ligne.prixUnitaire) {
        this.totalCommande += ligne.quantite * ligne.prixUnitaire;
      }
    });
  }

  onCategorieSelected(clientFournisseur: any) {
    this.selectedClientFournisseur = clientFournisseur;
  }

  onArticleSelected(article: any) {
    this.selectedArticle = article;
    this.prixUnitaire = article.prixUnitaireTtc;
  }

  findAll() {
    if (this.origin == 'client') {
      this.findAllClients();
    } else {
      this.findAllFournisseurs();
    }
  }

  getCommandeById(id: number) {
    if (this.origin == 'client') {
      this.getCommandeClientById(id);
    } else if (this.origin == 'fournisseur') {
      this.getCommandeFournisseurById(id);
    }
  }

  findAllArticles() {
    this.articleService.findAllArticlesList().subscribe({
      next: (articles) => {
        this.articles = articles;
      },
    });
  }

  findAllClients() {
    this.clientService.findAllClientsList().subscribe({
      next: (clients) => {
        this.clientsFournisseurs = clients as Array<ClientResponse>;
      },
    });
  }

  findAllFournisseurs() {
    this.fournisseurService.findAllFournisseursList().subscribe({
      next: (fournisseurs) => {
        this.clientsFournisseurs = fournisseurs as Array<FournisseurResponse>;
      },
    });
  }

  getCommandeClientById(id: number) {
    this.commandeClientService.findCommandeClientById(id).subscribe({
      next: (commande) => {},
    });
  }

  getCommandeFournisseurById(id: number) {
    this.commandeFournisseurService.findCommandeFournisseurById(id).subscribe({
      next: (commande) => {},
    });
  }

  onCancel(): void {
    if (this.origin == 'client') {
      this.router.navigate(['home', 'clients', 'commandes-client']);
    } else {
      this.router.navigate(['home', 'fournisseurs', 'commandes-fournisseur']);
    }
  }

  get getClientFournisseurPhoto(): string | undefined {
    if (this.selectedClientFournisseur.photo) {
      return 'data:image/jpg;base64,' + this.selectedClientFournisseur.photo;
    }
    return 'assets/images/profil.png';
  }

  ajouterLigneCommande(): void {
    this.checkLigneCommande();
    this.calculTotalCommande();
  }

  get selectedArticleValue(): string {
    return this.selectedArticle.value;
  }

  get selectedClientValue(): string {
    return this.selectedClientFournisseur.value;
  }

  saveCommande(): void {
    this.prepareCommande();
    if(this.origin==='client') {
      this.saveCommandeClient(this.commandeRequest as CommandeClientRequest);
    } else {
      this.saveCommandeFournisseur(this.commandeRequest as CommandeFournisseurRequest);
    }
  }

  saveCommandeFournisseur(request: CommandeFournisseurRequest): void {
    this.commandeFournisseurService.saveCommandeFournisseur(request).subscribe({
      next: (commandeFournisseurId) => {
        this.toastService.saveSuccess("CommandeFournisseur", this.commandeRequest.id);
          this.onCancel();
      },
      error: (err) => {
        if (err.error.validationErrors) {
          err.error.validationErrors.forEach((message: string) => {
            this.toastService.showWarning(message);
          });
        } else {
          this.toastService.showError(err.error.error);
        }
      }
    });
  }

  saveCommandeClient(request: CommandeClientRequest): void {
    this.commandeClientService.saveCommandeClient(request).subscribe({
      next: (commandeClientId) => {
        this.toastService.saveSuccess("CommandeClient", this.commandeRequest.id);
          this.onCancel();
      },
      error: (err) => {
        if (err.error.validationErrors) {
          err.error.validationErrors.forEach((message: string) => {
            this.toastService.showWarning(message);
          });
        } else {
          this.toastService.showError(err.error.error);
        }
      }
    });
  }
}
