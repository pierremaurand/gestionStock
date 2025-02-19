import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdresseRequest, UtilisateurRequest, UtilisateurResponse, UtilisateurService } from '../../../../services/openapi';
import { FormsModule } from '@angular/forms';
import { ToastService } from '../../../../services/toast/toast.service';

@Component({
  selector: 'app-nouveau-utilisateur',
  imports: [FormsModule],
  templateUrl: './nouveau-utilisateur.component.html',
  styleUrl: './nouveau-utilisateur.component.scss'
})
export class NouveauUtilisateurComponent implements OnInit{
  utilisateurRequest: any = {};
  adresse: any = {};
  selectedArticlePhoto: any;
  selectedPhoto: string | undefined;

  constructor(
    private router: Router,
    private utilisateurService: UtilisateurService,
    private activatedRoute: ActivatedRoute,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    const utilisateurId = this.activatedRoute.snapshot.params['utilisateurId'];
    if (utilisateurId) {
      this.utilisateurService.findUtilisateurById(utilisateurId).subscribe({
        next: (utilisateur) => {
          this.utilisateurRequest = {
            id: utilisateur.id,
            nom: utilisateur.nom as string,
            login: utilisateur.login as string,
            sexe: utilisateur.sexe as UtilisateurResponse.SexeEnum,
            numeroTel: utilisateur.numeroTel as string,
            email: utilisateur.email as string
          };
          this.adresse = utilisateur.adresse;
          if (utilisateur.photo) {
            this.selectedPhoto = 'data:image/jpg;base64,' + utilisateur.photo;
          }
        },
      });
    }
  }

  onCancel(): void {
    this.router.navigate(['home','parametres','utilisateurs']);
  }

  saveUtilisateur(): void {
    this.utilisateurRequest.adresse = this.adresse as AdresseRequest;
    this.utilisateurService.saveUtilisateur(this.utilisateurRequest as UtilisateurRequest).subscribe({
      next: (utilisateurId) => {
        if(this.selectedArticlePhoto) {
          this.utilisateurService
            .uploadUtilisateurPhoto(utilisateurId, this.selectedArticlePhoto)
            .subscribe({
              next: () => {
                this.toastService.saveSuccess("Utilisateur", this.utilisateurRequest.id);
                this.onCancel();
              },
            });
        } else {
          this.toastService.saveSuccess("Utilisateur", this.utilisateurRequest.id);
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

  disabled():boolean {
    if(this.utilisateurRequest.id) {
      return true;
    }
    return false;
  }

}
