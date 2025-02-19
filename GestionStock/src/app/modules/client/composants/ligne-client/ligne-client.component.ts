import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ClientResponse } from '../../../../services/openapi';

@Component({
  selector: 'app-ligne-client',
  imports: [],
  templateUrl: './ligne-client.component.html',
  styleUrl: './ligne-client.component.scss'
})
export class LigneClientComponent {

  private _client: ClientResponse = {};
  @Output() private edit: EventEmitter<ClientResponse> = new EventEmitter<ClientResponse>();
  @Output() private delete: EventEmitter<ClientResponse> = new EventEmitter<ClientResponse>();
  @Output() private details: EventEmitter<ClientResponse> = new EventEmitter<ClientResponse>();

  onEdit() {
    this.edit.emit(this._client);
  }

  onDelete(){
    this.delete.emit(this._client);
  }

  onDetails(){
    this.details.emit(this._client);
  }

  get client(): ClientResponse {
    return this._client;
  }

  get clientPhoto(): string | undefined {
    if(this._client.photo) {
      return 'data:image/jpg;base64,'+ this._client.photo;
    }
    return 'assets/images/profil.png';
  }


  @Input()
  set client(value: ClientResponse) {
    this._client = value;
  }

}
