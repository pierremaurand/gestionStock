import { Component } from '@angular/core';
import { DetailsArticleComponent } from '../../composants/details-article/details-article.component';
import { PaginationComponent } from '../../../../composants/pagination/pagination.component';
import { BouttonsActionComponent } from '../../../../composants/bouttons-action/bouttons-action.component';

@Component({
  selector: 'app-articles',
  imports: [DetailsArticleComponent, PaginationComponent, BouttonsActionComponent],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.scss'
})
export class ArticlesComponent {

}
