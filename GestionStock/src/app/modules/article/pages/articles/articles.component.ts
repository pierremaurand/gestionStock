import { Component } from '@angular/core';
import { DetailsArticleComponent } from '../../composants/details-article/details-article.component';
import { PaginationComponent } from '../../../../composants/pagination/pagination.component';
import { BouttonsActionComponent } from '../../../../composants/bouttons-action/bouttons-action.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-articles',
  imports: [DetailsArticleComponent, PaginationComponent, BouttonsActionComponent],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.scss'
})
export class ArticlesComponent {

  constructor(private router:Router){}

  nouvelArticle(): void{
    this.router.navigate(['home/articles/nouvel-article']);
  }
}
