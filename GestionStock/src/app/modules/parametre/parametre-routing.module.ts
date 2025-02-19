import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriesComponent } from './pages/categories/categories.component';
import { UtilisateursComponent } from './pages/utilisateurs/utilisateurs.component';
import { NouvelleCategorieComponent } from './pages/nouvelle-categorie/nouvelle-categorie.component';
import { NouveauUtilisateurComponent } from './pages/nouveau-utilisateur/nouveau-utilisateur.component';
import { ProfilComponent } from './pages/profil/profil.component';
import { ChangerMotDePasseComponent } from './pages/changer-mot-de-passe/changer-mot-de-passe.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'categories',
    pathMatch: 'full'
  },
  {
    path: 'categories',
    component: CategoriesComponent
  },
  {
    path: 'utilisateurs',
    component: UtilisateursComponent
  },
  {
    path: 'nouvelle-categorie',
    component: NouvelleCategorieComponent
  },
  {
    path: 'nouvelle-categorie/:categorieId',
    component: NouvelleCategorieComponent
  },
  {
    path: 'nouveau-utilisateur',
    component: NouveauUtilisateurComponent
  },
  {
    path: 'nouveau-utilisateur/:utilisateurId',
    component: NouveauUtilisateurComponent
  },
  {
    path: 'profil',
    component: ProfilComponent
  },
  {
    path: 'changer-mot-de-passe',
    component: ChangerMotDePasseComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParametreRoutingModule { }
