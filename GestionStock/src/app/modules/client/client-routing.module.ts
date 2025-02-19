import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientsComponent } from './pages/clients/clients.component';
import { CommandesClientComponent } from './pages/commandes-client/commandes-client.component';
import { NouvelleCommandeClientFournisseurComponent } from '../../composants/nouvelle-commande-client-fournisseur/nouvelle-commande-client-fournisseur.component';
import { NouveauClientComponent } from './pages/nouveau-client/nouveau-client.component';
import { NouveauClientFournisseurComponent } from '../../composants/nouveau-client-fournisseur/nouveau-client-fournisseur.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'clients',
    pathMatch: 'full'
  },
  {
    path: 'clients',
    component: ClientsComponent
  },
  {
    path: 'commandes-client',
    component: CommandesClientComponent
  },
  {
    path: 'nouveau-client',
    component: NouveauClientFournisseurComponent,
    data: {
      origin: 'client'
    }
  },
  {
    path: 'nouveau-client/:clientFournisseurId',
    component: NouveauClientFournisseurComponent,
    data: {
      origin: 'client'
    }
  },
  {
    path: 'nouvelle-commande',
    component: NouvelleCommandeClientFournisseurComponent,
    data: {
      origin: 'client'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
