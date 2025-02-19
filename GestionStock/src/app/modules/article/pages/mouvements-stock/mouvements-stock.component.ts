import { MouvementStockResponse } from './../../../../services/openapi/model/mouvement-stock-response';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PaginationComponent } from '../../../../composants/pagination/pagination.component';
import { BouttonsActionComponent } from '../../../../composants/bouttons-action/bouttons-action.component';
import { LigneMouvementStockComponent } from '../../composants/ligne-mouvement-stock/ligne-mouvement-stock.component';
import { ArticleService, MouvementStockRequest, MouvementStockService, PageResponseArticleResponse } from '../../../../services/openapi';
import { ToastService } from '../../../../services/toast/toast.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-mouvements-stock',
  imports: [CommonModule, FormsModule, PaginationComponent, BouttonsActionComponent, LigneMouvementStockComponent],
  templateUrl: './mouvements-stock.component.html',
  styleUrl: './mouvements-stock.component.scss'
})
export class MouvementsStockComponent implements OnInit{
  mouvements: Array<MouvementStockResponse> = [];
  articleResponse: PageResponseArticleResponse = {};
  nouveauMouvement: any = {};
  page = 0;
  size = 10;
  pages: any = [];

  constructor(
    private articleService: ArticleService,
    private mouvementStockService: MouvementStockService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.findAllMouvements();
  }

  private findAllMouvements() {
    this.mouvementStockService.findAllMouvementStocks().subscribe({
      next: (mouvements) => {
        this.mouvements = mouvements;
        this.findAllArticles();
      }
    });
  }

  private findAllArticles() {
    this.articleService.findAllArticles(this.page, this.size).subscribe({
      next: (articles) => {
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

  onCorrectStockClicked(articleId: number) {
    const today = new Date();
    this.nouveauMouvement = {
      dateMouvement: today.toISOString().substring(0,10),
      article: articleId,
      provenance: 'CORRECTION',
      typeMouvement: ""
    };
  }

  saveMouvement() {
    this.mouvementStockService.saveMouvementStock(this.nouveauMouvement as MouvementStockRequest).subscribe({
      next: () => {
        this.toastService.showSuccess("Mouvement ajouté avec succès");
        this.findAllMouvements();
      }
    });
  }
}
