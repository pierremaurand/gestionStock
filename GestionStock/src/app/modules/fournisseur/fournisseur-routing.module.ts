import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FournisseursComponent } from './pages/fournisseurs/fournisseurs.component';
import { CommandesFournisseurComponent } from './pages/commandes-fournisseur/commandes-fournisseur.component';

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
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FournisseurRoutingModule { }
