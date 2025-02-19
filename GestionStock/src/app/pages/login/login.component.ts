import { ToastService } from './../../services/toast/toast.service';
import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { Router } from '@angular/router';
import { TokenService } from '../../services/token/token.service';
import { AuthenticationRequest, AuthenticationResponse, AuthenticationService, UtilisateurResponse } from '../../services/openapi';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {login: "", motDePasse: ""};

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService,
    private toastService: ToastService
  ){}

  login(): void {
    this.authService.login(this.authRequest)
    .subscribe({
      next: (res: AuthenticationResponse) => {
        this.tokenService.token = res.token as string;
        this.tokenService.utilisateur = res.utilisateur as UtilisateurResponse;
        this.toastService.showSuccess("La connexion de l'utilisateur réussie");
        this.router.navigate(['home']);
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
    })
  }
}
