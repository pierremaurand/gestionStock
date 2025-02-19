import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FournisseursComponent } from './pages/fournisseurs/fournisseurs.component';
import { CommandesFournisseurComponent } from './pages/commandes-fournisseur/commandes-fournisseur.component';
import { NouveauFournisseurComponent } from './pages/nouveau-fournisseur/nouveau-fournisseur.component';
import { NouveauClientFournisseurComponent } from '../../composants/nouveau-client-fournisseur/nouveau-client-fournisseur.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'fournisseurs',
    pathMatch: 'full'
  },
  {
    path: 'fournisseurs',
    component: FournisseursComponent
  },
  {
    path: 'commandes-fournisseur',
    component: CommandesFournisseurComponent
  },
  {
    path: 'nouveau-fournisseur',
    component: NouveauClientFournisseurComponent,
    data: {
      origin: 'fournisseur'
    }
  },
  {
    path: 'nouveau-fournisseur/:clientFournisseurId',
    component: NouveauClientFournisseurComponent,
    data: {
      origin: 'fournisseur'
    }
  },
  {
    path: 'nouvelle-commande',
    component: NouveauFournisseurComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FournisseurRoutingModule { }
