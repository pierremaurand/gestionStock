import { Component, OnInit } from '@angular/core';
import { DetailsArticleComponent } from '../../composants/details-article/details-article.component';
import { PaginationComponent } from '../../../../composants/pagination/pagination.component';
import { BouttonsActionComponent } from '../../../../composants/bouttons-action/bouttons-action.component';
import { Router } from '@angular/router';
import { ArticleService } from '../../../../services/services';
import {
  ArticleResponse,
  PageResponseArticleResponse,
} from '../../../../services/models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-articles',
  imports: [
    CommonModule,
    DetailsArticleComponent,
    PaginationComponent,
    BouttonsActionComponent,
  ],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.scss',
})
export class ArticlesComponent implements OnInit {
  articleResponse: PageResponseArticleResponse = {};
  page = 0;
  size = 10;
  pages: any = [];
  message = '';
  level: 'success' | 'error' = 'success';

  constructor(private router: Router, private articleService: ArticleService) {}

  ngOnInit(): void {
    this.findAllArticles();
  }

  nouvelArticle(): void {
    this.router.navigate(['home/articles/nouvel-article']);
  }

  private findAllArticles() {
    this.articleService
      .findAll8({
        page: this.page,
        size: this.size,
      })
      .subscribe({
        next: (articles) => {
          console.log(articles);
          this.articleResponse = articles;
          this.pages = Array(this.articleResponse.totalElements)
            .fill(0)
            .map((x, i) => i);
        },
      });
  }

  goToPage(page: number) {
    this.page = page;
    this.findAllArticles();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllArticles();
  }

  goToLastPage() {
    this.page = (this.articleResponse.totalPages as number) - 1;
    this.findAllArticles();
  }

  goToPreviousPage() {
    this.page--;
    this.findAllArticles();
  }

  goToNextPage() {
    this.page++;
    this.findAllArticles();
  }

  get isLastPage() {
    return this.page === (this.articleResponse.totalPages as number) - 1;
  }
}
