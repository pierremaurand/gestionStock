import { Component, Input } from '@angular/core';
import { ArticleResponse } from '../../../../services/models';

@Component({
  selector: 'app-details-article',
  imports: [],
  templateUrl: './details-article.component.html',
  styleUrl: './details-article.component.scss',
})
export class DetailsArticleComponent {
  private _article: ArticleResponse = {};

  get article(): ArticleResponse {
    return this._article;
  }

  @Input()
  set article(value: ArticleResponse) {
    this._article = value;
  }
}
