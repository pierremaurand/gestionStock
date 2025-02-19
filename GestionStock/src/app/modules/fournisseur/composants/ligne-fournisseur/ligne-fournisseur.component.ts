import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FournisseurResponse } from '../../../../services/openapi';

@Component({
  selector: 'app-ligne-fournisseur',
  imports: [],
  templateUrl: './ligne-fournisseur.component.html',
  styleUrl: './ligne-fournisseur.component.scss'
})
export class LigneFournisseurComponent {

  private _fournisseur: FournisseurResponse = {};
  @Output() private edit: EventEmitter<FournisseurResponse> = new EventEmitter<FournisseurResponse>();
  @Output() private delete: EventEmitter<FournisseurResponse> = new EventEmitter<FournisseurResponse>();
  @Output() private details: EventEmitter<FournisseurResponse> = new EventEmitter<FournisseurResponse>();

  onEdit() {
    this.edit.emit(this._fournisseur);
  }

  onDelete(){
    this.delete.emit(this._fournisseur);
  }

  onDetails(){
    this.details.emit(this._fournisseur);
  }

  get fournisseur(): FournisseurResponse {
    return this._fournisseur;
  }

  get fournisseurPhoto(): string | undefined {
    if(this._fournisseur.photo) {
      return 'data:image/jpg;base64,'+ this._fournisseur.photo;
    }
    return 'assets/images/profil.png';
  }


  @Input()
  set fournisseur(value: FournisseurResponse) {
    this._fournisseur = value;
  }

}
