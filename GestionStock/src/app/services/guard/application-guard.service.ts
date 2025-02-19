import { inject, Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { TokenService } from '../token/token.service';

@Injectable({
  providedIn: 'root',
})
export class ApplicationGuardService implements CanActivate {
  constructor(private tokenService: TokenService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): MaybeAsync<GuardResult> {
    if (this.isUserLoggedAndTokenValid()) {
      return true;
    }
    this.tokenService.logout();
    this.router.navigate(['login']);
    return false;
  }

  isUserLoggedAndTokenValid(): boolean {
    const token = this.tokenService.token;
    if (token) {
      const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
      return expiry * 1000 > Date.now();
    }
    return false;
  }
}
