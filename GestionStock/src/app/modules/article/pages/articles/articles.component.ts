import { Component, Inject, OnInit } from '@angular/core';
import { PaginationComponent } from '../../../../composants/pagination/pagination.component';
import { BouttonsActionComponent } from '../../../../composants/bouttons-action/bouttons-action.component';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {
  ArticleResponse,
  ArticleService,
  PageResponseArticleResponse,
} from '../../../../services/openapi';
import { LigneArticleComponent } from '../../composants/ligne-article/ligne-article.component';
import { ToastService } from '../../../../services/toast/toast.service';

@Component({
  selector: 'app-articles',
  imports: [
    CommonModule,
    LigneArticleComponent,
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

  constructor(
    private router: Router,
    private articleService: ArticleService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.findAllArticles();
  }

  nouvelArticle(): void {
    this.router.navigate(['home', 'articles', 'nouvel-article']);
  }

  private findAllArticles() {
    this.articleService.findAllArticles(this.page, this.size).subscribe({
      next: (articles) => {
        console.log(articles);
        this.articleResponse = articles;
        this.pages = Array(this.articleResponse.totalPages)
          .fill(0)
          .map((x, i) => i);
      },
    });
  }

  goToPage(page: number) {
    this.page = page;
    this.findAllArticles();
  }

  edit(article: ArticleResponse) {
    this.router.navigate(['home', 'articles', 'nouvel-article', article.id]);
  }

  delete(article: ArticleResponse) {
    this.articleService.deleteArticle(article.id as number).subscribe({
      next: () => {
        this.toastService.showSuccess("Article supprimé aves success");
        this.findAllArticles();
      },
      error: (err) => {
        this.toastService.showError(err.error.error);
      },
    });
  }

  details(article: ArticleResponse) {}
}
