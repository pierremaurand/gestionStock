import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { AuthenticationRequest, AuthenticationResponse } from '../../services/models';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
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
    this.authService.login({
      body: this.authRequest
    }).subscribe({
      next: (res: AuthenticationResponse) => {
        console.log(res);
        this.tokenService.token = res.token as string;
        this.router.navigate(['home']);
      },
      error: (err) => {
        console.log(err);
      }
    })
  }
}
