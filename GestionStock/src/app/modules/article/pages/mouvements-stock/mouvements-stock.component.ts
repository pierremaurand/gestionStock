import { Component } from '@angular/core';
import { PaginationComponent } from '../../../../composants/pagination/pagination.component';
import { BouttonsActionComponent } from '../../../../composants/bouttons-action/bouttons-action.component';
import { DetailsMouvementsStockComponent } from '../../composants/details-mouvements-stock/details-mouvements-stock.component';

@Component({
  selector: 'app-mouvements-stock',
  imports: [PaginationComponent, BouttonsActionComponent, DetailsMouvementsStockComponent],
  templateUrl: './mouvements-stock.component.html',
  styleUrl: './mouvements-stock.component.scss'
})
export class MouvementsStockComponent {

}
