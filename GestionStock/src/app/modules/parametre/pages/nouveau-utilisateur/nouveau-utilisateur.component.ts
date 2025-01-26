import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nouveau-utilisateur',
  imports: [],
  templateUrl: './nouveau-utilisateur.component.html',
  styleUrl: './nouveau-utilisateur.component.scss'
})
export class NouveauUtilisateurComponent {

  constructor(private router: Router){}

  onCancel(): void {
    this.router.navigate(['home/parametres/utilisateurs']);
  }

}
