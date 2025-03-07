import { CommonModule } from '@angular/common';
import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-autocomplete',
  imports: [CommonModule, FormsModule],
  templateUrl: './autocomplete.component.html',
  styleUrl: './autocomplete.component.scss'
})
export class AutocompleteComponent {
  @Input()
  placeholder: string = '';
  @Input()
  liste: Array<any> = [];
  listeFiltree: Array<any> = [];
  value: string = '';

  @Output()
  elementSelected = new EventEmitter<any>();

  onElementSelected(obj: any) {
    this.elementSelected.emit(obj);
    this.value = obj.value;
    this.listeFiltree = [];
  }

  filtrerList(): void {
    if(this.value.length === 0) {
      this.elementSelected.emit({});
      this.listeFiltree = [];
    } else {
      this.listeFiltree = this.liste.filter(elt => elt.value.toLowerCase().includes(this.value.toLowerCase()));
    }
  }

  @Input()
  set selectedValue(value: string) {
    this.value = value;
  }
 }
