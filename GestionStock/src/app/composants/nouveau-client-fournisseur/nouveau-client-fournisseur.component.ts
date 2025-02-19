import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  AdresseRequest,
  ClientRequest,
  ClientResponse,
  ClientService,
  FournisseurRequest,
  FournisseurResponse,
  FournisseurService,
} from '../../services/openapi';
import { FormsModule } from '@angular/forms';
import { ToastService } from '../../services/toast/toast.service';

@Component({
  selector: 'app-nouveau-client-fournisseur',
  imports: [FormsModule],
  templateUrl: './nouveau-client-fournisseur.component.html',
  styleUrl: './nouveau-client-fournisseur.component.scss',
})
export class NouveauClientFournisseurComponent implements OnInit {
  clientFournisseurRequest: any = {
    sexe: ""
  };
  adresse: any = {};
  selectedArticlePhoto: any;
  selectedPhoto: string | undefined;

  origin = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private fournisseurService: FournisseurService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe((data) => {
      this.origin = data['origin'];
      const clientFournisseurId =
        this.activatedRoute.snapshot.params['clientFournisseurId'];
      if (clientFournisseurId) {
       this.getClientFournisseurById(clientFournisseurId);
      }
    });
  }

  getClientFournisseurById(id: number) {
    if (this.origin == 'client') {
      this.getClientById(id);
    } else if(this.origin == 'fournisseur') {
      this.getFournisseurById(id);
    }
  }

  onCancel(): void {
    if (this.origin == 'client') {
      this.router.navigate(['home', 'clients']);
    } else if(this.origin == 'fournisseur'){
      this.router.navigate(['home', 'fournisseurs']);
    }
  }

  onFileSelected(event: any) {
    this.selectedArticlePhoto = event.target.files[0];
    console.log(this.selectedArticlePhoto);

    if (this.selectedArticlePhoto) {
      const reader = new FileReader();
      reader.onload = () => {
        this.selectedPhoto = reader.result as string;
      };
      reader.readAsDataURL(this.selectedArticlePhoto);
    }
  }

  saveClient(request: ClientRequest): void {
    this.clientService.saveClient(request).subscribe({
      next: (clientId) => {
        if(this.selectedArticlePhoto) {
          this.clientService
            .uploadClientPhoto(clientId, this.selectedArticlePhoto)
            .subscribe({
              next: () => {
                this.toastService.saveSuccess("Client", this.clientFournisseurRequest.id);
                this.onCancel();
              },
            });
        } else {
          this.toastService.saveSuccess("Client", this.clientFournisseurRequest.id);
          this.onCancel();
        }
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

  saveFournisseur(request: FournisseurRequest): void {
    this.fournisseurService.saveFournisseur(request).subscribe({
      next: (fournisseurId) => {
        if(this.selectedArticlePhoto) {
          this.fournisseurService
            .uploadFournisseurPhoto(fournisseurId, this.selectedArticlePhoto)
            .subscribe({
              next: () => {
                this.toastService.saveSuccess("Fournisseur", this.clientFournisseurRequest.id);
                this.onCancel();
              },
            });
        } else {
          this.toastService.saveSuccess("Fournisseur", this.clientFournisseurRequest.id);
          this.onCancel();
        }
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

  getClientById(id: number): void {
    this.clientService.findClientById(id).subscribe({
      next: (client) => {
        this.clientFournisseurRequest = {
          id: client.id,
          nom: client.nom as string,
          numeroCNI: client.numeroCNI as string,
          sexe: client.sexe as ClientResponse.SexeEnum,
          numeroTel: client.numeroTel as string,
          email: client.email as string
        };
        this.adresse = {
          adresse1: client.adresse?.adresse1 as string,
          adresse2: client.adresse?.adresse2 as string,
          codePostale: client.adresse?.codePostale,
          ville: client.adresse?.ville as string,
          pays: client.adresse?.pays
        };
        if (client.photo) {
          this.selectedPhoto = 'data:image/jpg;base64,' + client.photo;
        }
      },
    });
  }

  getFournisseurById(id: number): void {
    this.fournisseurService.findFournisseurById(id).subscribe({
      next: (fournisseur) => {
        this.clientFournisseurRequest = {
          id: fournisseur.id,
          nom: fournisseur.nom as string,
          numeroCNI: fournisseur.numeroCNI as string,
          sexe: fournisseur.sexe as FournisseurResponse.SexeEnum,
          numeroTel: fournisseur.numeroTel as string,
          email: fournisseur.email as string
        };
        this.adresse = {
          adresse1: fournisseur.adresse?.adresse1 as string,
          adresse2: fournisseur.adresse?.adresse2 as string,
          codePostale: fournisseur.adresse?.codePostale,
          ville: fournisseur.adresse?.ville as string,
          pays: fournisseur.adresse?.pays
        };
        if (fournisseur.photo) {
          this.selectedPhoto = 'data:image/jpg;base64,' + fournisseur.photo;
        }
      },
    });
  }

  save(): void {
    this.clientFournisseurRequest.adresse = this.adresse as AdresseRequest;
    if (this.origin == 'client') {
      this.saveClient(this.clientFournisseurRequest as ClientRequest);
    } else if(this.origin == 'fournisseur'){
      this.saveFournisseur(this.clientFournisseurRequest as FournisseurRequest);
    }
  }
}
