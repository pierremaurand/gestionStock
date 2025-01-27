import { NgClass, NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-bouttons-action',
  imports: [NgIf, NgClass],
  templateUrl: './bouttons-action.component.html',
  styleUrl: './bouttons-action.component.scss'
})
export class BouttonsActionComponent {

  @Input()
  isNouveauVisible = true;
  @Input()
  isImporterVisible = true;
  @Input()
  isExporterVisible = true;

  @Output()
  clickEvent = new EventEmitter();

  bouttonNouveauClick(){
    this.clickEvent.emit();
  }

}
