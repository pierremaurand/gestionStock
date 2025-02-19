import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CategorieResponse } from '../../../../services/openapi';

@Component({
  selector: 'app-ligne-categorie',
  imports: [],
  templateUrl: './ligne-categorie.component.html',
  styleUrl: './ligne-categorie.component.scss'
})
export class LigneCategorieComponent {
  private _categorie: CategorieResponse = {};
  @Output() private edit: EventEmitter<CategorieResponse> = new EventEmitter<CategorieResponse>();
  @Output() private delete: EventEmitter<CategorieResponse> = new EventEmitter<CategorieResponse>();
  @Output() private details: EventEmitter<CategorieResponse> = new EventEmitter<CategorieResponse>();

  onEdit() {
    this.edit.emit(this._categorie);
  }

  onDelete(){
    this.delete.emit(this._categorie);
  }

  onDetails(){
    this.details.emit(this._categorie);
  }

  get categorie(): CategorieResponse {
    return this._categorie;
  }

  @Input()
  set categorie(value: CategorieResponse) {
    this._categorie = value;
  }

}
