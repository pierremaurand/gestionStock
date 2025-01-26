import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-bouttons-action',
  imports: [],
  templateUrl: './bouttons-action.component.html',
  styleUrl: './bouttons-action.component.scss'
})
export class BouttonsActionComponent {
  @Output()
  clickEvent = new EventEmitter();

  bouttonNouveauClick(){
    this.clickEvent.emit();
  }

}
