import { Component, Input } from '@angular/core';
import { MouvementStockResponse } from '../../../../services/openapi';

@Component({
  selector: 'app-details-mouvement-stock',
  imports: [],
  templateUrl: './details-mouvement-stock.component.html',
  styleUrl: './details-mouvement-stock.component.scss'
})
export class DetailsMouvementStockComponent {
  private _mouvement: MouvementStockResponse = {};

  get mouvement() {
    return this._mouvement;
  }

  @Input()
  set mouvement(value: MouvementStockResponse) {
    this._mouvement = value;
  }
}
