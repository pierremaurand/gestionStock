import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profil',
  imports: [],
  templateUrl: './profil.component.html',
  styleUrl: './profil.component.scss'
})
export class ProfilComponent {

  constructor(private router: Router){}

  modifierMotDePasse(): void {
    this.router.navigate(['home/parametres/changer-mot-de-passe']);
  }

}
