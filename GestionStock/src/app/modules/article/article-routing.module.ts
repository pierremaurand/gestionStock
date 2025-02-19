import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArticlesComponent } from './pages/articles/articles.component';
import { MouvementsStockComponent } from './pages/mouvements-stock/mouvements-stock.component';
import { NouvelArticleComponent } from './pages/nouvel-article/nouvel-article.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'articles',
    pathMatch: 'full'
  },
  {
    path: 'articles',
    component: ArticlesComponent
  },
  {
    path: 'nouvel-article',
    component: NouvelArticleComponent
  },
  {
    path: 'nouvel-article/:articleId',
    component: NouvelArticleComponent
  },
  {
    path: 'mouvements-stock',
    component: MouvementsStockComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticleRoutingModule { }
