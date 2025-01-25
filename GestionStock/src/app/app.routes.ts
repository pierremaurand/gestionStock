import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { MainComponent } from './pages/main/main.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: MainComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)
      },
      {
        path: 'articles',
        loadChildren: () => import('./modules/article/article.module').then(m => m.ArticleModule)
      },
      {
        path: 'clients',
        loadChildren: () => import('./modules/client/client.module').then(m => m.ClientModule)
      },
      {
        path: 'fournisseurs',
        loadChildren: () => import('./modules/fournisseur/fournisseur.module').then(m => m.FournisseurModule)
      },
      {
        path: 'parametres',
        loadChildren: () => import('./modules/parametre/parametre.module').then(m => m.ParametreModule)
      }
    ]
  }
];
