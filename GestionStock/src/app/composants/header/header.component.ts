import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../services/token/token.service';
import { UtilisateurResponse } from '../../services/openapi';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit{
  private _utilisateur: UtilisateurResponse = {};

  constructor(private router: Router, private tokenService: TokenService) {}

  ngOnInit(): void {
    this._utilisateur = this.tokenService.utilisateur;
  }

  get utilisateur(): UtilisateurResponse{
    return this._utilisateur;
  }

  get utilisateurPhoto(): string | undefined {
    if(this._utilisateur.photo) {
      return 'data:image/jpg;base64,'+ this._utilisateur.photo;
    }
    return 'assets/images/profil.png';
  }

  openProfil(): void {
    this.router.navigate(['home/parametres/profil']);
  }
}
