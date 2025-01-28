import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../services/token/token.service';
import { AuthenticationRequest, AuthenticationResponse } from '../../services/models';
import { AuthenticationService } from '../../services/services';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {login: "", motDePasse: ""};
  errorMsg: Array<String> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ){}

  login(): void {
    this.errorMsg = [];
    this.authService.login({
      body: this.authRequest
    }).subscribe({
      next: (res: AuthenticationResponse) => {
        console.log(res.token);
        this.tokenService.token = res.token as string;
        this.router.navigate(['home']);
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

}
