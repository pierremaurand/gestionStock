import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'login',
    loadComponent: () => import('./ui/pages/login/login.component'),
  },
  {
    path: 'home',
    loadComponent: () => import('./ui/pages/main/main.component'),
  },
];
