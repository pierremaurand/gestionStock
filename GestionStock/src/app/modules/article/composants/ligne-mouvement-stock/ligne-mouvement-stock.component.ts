import { MouvementStockRequest } from './../../../../services/openapi/model/mouvement-stock-request';
import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DetailsMouvementStockComponent } from '../details-mouvement-stock/details-mouvement-stock.component';
import { ArticleResponse, MouvementStockResponse } from '../../../../services/openapi';

@Component({
  selector: 'app-ligne-mouvement-stock',
  imports: [CommonModule,DetailsMouvementStockComponent],
  templateUrl: './ligne-mouvement-stock.component.html',
  styleUrl: './ligne-mouvement-stock.component.scss'
})
export class LigneMouvementStockComponent{
  private _article: ArticleResponse = {};
  private _mouvements: Array<MouvementStockResponse> = [];
  @Output() private correctStock: EventEmitter<number> = new EventEmitter<number>();

  correctStockClicked() {
    this.correctStock.emit(this._article.id as number);
  }

  get articlePhoto(): string | undefined {
    if(this._article.photo) {
      return 'data:image/jpg;base64,'+ this._article.photo;
    }
    return 'assets/images/product.png';
  }

  get article(): ArticleResponse {
    return this._article;
  }

  get mouvements(): Array<MouvementStockResponse> {
    if(this._article.id) {
      return this._mouvements.filter((mouvement) => mouvement.article == this._article.id);
    }
    return [];
  }

  get stock(): number {
    return +this.getTotalType('ENTREE')+this.getTotalType('CORRECTION_POS')
      -this.getTotalType('SORTIE')-this.getTotalType('CORRECTION_NEG');
  }

  private getTotalType(type: MouvementStockRequest.TypeMouvementEnum): number {
    let total = 0;
    this.mouvements.filter((mouvement) => mouvement.typeMouvement == type)
    .forEach((mouvement) => {
      total += mouvement.quantite as number;
    })

    return total;
  }

  @Input()
  set article(value: ArticleResponse) {
    this._article = value;
  }

  @Input()
  set mouvements(value: Array<MouvementStockResponse>) {
    this._mouvements = value;
  }
}
