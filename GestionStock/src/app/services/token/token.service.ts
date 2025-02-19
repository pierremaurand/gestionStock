import { Injectable } from '@angular/core';
import { UtilisateurResponse } from '../openapi';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  set utilisateur(utilisateur: UtilisateurResponse){
    localStorage.setItem("utilisateur", JSON.stringify(utilisateur));
  }

  get utilisateur(): UtilisateurResponse{
    const utilisateur = localStorage.getItem("utilisateur") as string;
    return JSON.parse(utilisateur);
  }

  set token(token: string) {
    localStorage.setItem("token", token);
  }

  get token(): string {
    return localStorage.getItem("token") as string;
  }

  logout(): void {
    localStorage.clear();
  }
}
