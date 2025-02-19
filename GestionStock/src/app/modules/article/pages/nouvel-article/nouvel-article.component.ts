import { ToastService } from './../../../../services/toast/toast.service';
import { ArticleRequest } from './../../../../services/openapi/model/article-request';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import {
  ArticleService,
  CategorieResponse,
  CategorieService,
} from '../../../../services/openapi';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-nouvel-article',
  imports: [CommonModule, FormsModule],
  templateUrl: './nouvel-article.component.html',
  styleUrl: './nouvel-article.component.scss',
})
export class NouvelArticleComponent implements OnInit {
  articleRequest: any = {};
  categories: Array<CategorieResponse> = [];
  selectedArticlePhoto: any;
  selectedPhoto: string | undefined;
  prixUnitaireTTC: string = '';
  codeCategorie: string = '';
  selectedCategorie: CategorieResponse = {};
  categorieNotYetSelected = false;

  constructor(
    private router: Router,
    private articleService: ArticleService,
    private categorieService: CategorieService,
    private activatedRoute: ActivatedRoute,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    const articleId = this.activatedRoute.snapshot.params['articleId'];
    if (articleId) {
      this.articleService.findArticleById(articleId as number).subscribe({
        next: (article) => {
          this.articleRequest = {
            id: article.id,
            code: article.code as string,
            designation: article.designation as string,
            prixUnitaireHt: article.prixUnitaireHt as number,
            tauxTva: article.tauxTva as number,
            categorie: article.categorie?.id as number,
          };
          if (article.photo) {
            this.selectedPhoto = 'data:image/jpg;base64,' + article.photo;
          }
          this.prixUnitaireTTC = article.prixUnitaireTtc
            ? article.prixUnitaireTtc.toString()
            : '';
        },
      });
    } else {
      this.articleRequest = {
        code: this.generateCode(),
      };
    }
    this.findAllCategories();
  }

  selectCategorieClick(categorie: CategorieResponse) {

  }

  filterCategorie(): void {
    if (this.codeCategorie.length === 0) {
      this.findAllCategories();
    }
    this.categories = this.categories.filter(
      (cat) =>
        cat.code?.includes(this.codeCategorie) ||
        cat.designation?.includes(this.codeCategorie)
    );
  }

  generateCode(): string {
    return 'ART' + Date.now();
  }

  findAllCategories() {
    this.categorieService.findCategoriesList().subscribe({
      next: (categories) => {
        this.categories = categories;
      },
    });
  }

  onCancel(): void {
    this.router.navigate(['home', 'articles']);
  }

  calculPrixUnitaireTTC(): void {
    if (this.articleRequest.prixUnitaireHt && this.articleRequest.tauxTva) {
      this.prixUnitaireTTC = Math.round(
        +this.articleRequest.prixUnitaireHt *
          (1 + this.articleRequest.tauxTva / 100)
      ).toString();
    }
  }

  saveArticle(): void {
    this.articleService
      .saveArticle(this.articleRequest as ArticleRequest)
      .subscribe({
        next: (articleId) => {
          if (this.selectedArticlePhoto) {
            this.articleService
              .uploadArticlePhoto(articleId, this.selectedArticlePhoto)
              .subscribe({
                next: () => {
                  this.toastService.saveSuccess(
                    'Article',
                    this.articleRequest.id
                  );
                  this.onCancel();
                },
              });
          } else {
            this.toastService.saveSuccess('Article', this.articleRequest.id);
            this.onCancel();
          }
        },
        error: (err) => {
          if (err.error.validationErrors) {
            err.error.validationErrors.forEach((message: string) => {
              this.toastService.showWarning(message);
            });
          } else {
            this.toastService.showError(err.error.error);
          }
        },
      });
  }

  onFileSelected(event: any) {
    this.selectedArticlePhoto = event.target.files[0];
    console.log(this.selectedArticlePhoto);

    if (this.selectedArticlePhoto) {
      const reader = new FileReader();
      reader.onload = () => {
        this.selectedPhoto = reader.result as string;
      };
      reader.readAsDataURL(this.selectedArticlePhoto);
    }
  }
}
