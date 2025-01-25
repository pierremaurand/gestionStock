import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VueEnsembleComponent } from './pages/vue-ensemble/vue-ensemble.component';
import { StatistiquesComponent } from './pages/statistiques/statistiques.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'vueensemble',
    pathMatch: 'full'
  },
  {
    path: 'vueensemble',
    component: VueEnsembleComponent
  },
  {
    path: 'statistiques',
    component: StatistiquesComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
