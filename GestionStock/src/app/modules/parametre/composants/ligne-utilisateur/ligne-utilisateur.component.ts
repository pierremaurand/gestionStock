import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UtilisateurResponse } from '../../../../services/openapi';

@Component({
  selector: 'app-ligne-utilisateur',
  imports: [],
  templateUrl: './ligne-utilisateur.component.html',
  styleUrl: './ligne-utilisateur.component.scss'
})
export class LigneUtilisateurComponent {
  private _utilisateur: UtilisateurResponse = {};
  @Output() private edit: EventEmitter<UtilisateurResponse> = new EventEmitter<UtilisateurResponse>();
  @Output() private delete: EventEmitter<UtilisateurResponse> = new EventEmitter<UtilisateurResponse>();
  @Output() private details: EventEmitter<UtilisateurResponse> = new EventEmitter<UtilisateurResponse>();

  onEdit() {
    this.edit.emit(this._utilisateur);
  }

  onDelete(){
    this.delete.emit(this._utilisateur);
  }

  onDetails(){
    this.details.emit(this._utilisateur);
  }

  get utilisateur(): UtilisateurResponse {
    return this._utilisateur;
  }

  get utilisateurPhoto(): string | undefined {
    if(this._utilisateur.photo) {
      return 'data:image/jpg;base64,'+ this._utilisateur.photo;
    }
    return 'assets/images/profil.png';
  }


  @Input()
  set utilisateur(value: UtilisateurResponse) {
    this._utilisateur = value;
  }
}
