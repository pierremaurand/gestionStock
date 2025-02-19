import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ArticleResponse } from '../../../../services/openapi';

@Component({
  selector: 'app-details-article',
  imports: [],
  templateUrl: './details-article.component.html',
  styleUrl: './details-article.component.scss',
})
export class DetailsArticleComponent {
  private _article: ArticleResponse = {};
  @Output() private edit: EventEmitter<ArticleResponse> = new EventEmitter<ArticleResponse>();
  @Output() private delete: EventEmitter<ArticleResponse> = new EventEmitter<ArticleResponse>();
  @Output() private details: EventEmitter<ArticleResponse> = new EventEmitter<ArticleResponse>();

  onEdit() {
    this.edit.emit(this._article);
  }

  onDelete(){
    this.delete.emit(this._article);
  }

  onDetails(){
    this.details.emit(this._article);
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

  @Input()
  set article(value: ArticleResponse) {
    this._article = value;
  }
}
