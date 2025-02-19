import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  imports: [CommonModule],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent {
  @Input() page = 0;
  @Input() pages: any = [];
  @Input() totalPages: number|undefined = 0;

  @Output() goToPageEvent: EventEmitter<number> = new EventEmitter<number>();

  goToPage(page: number) {
    this.page = page;
    this.goToPageEvent.emit(this.page);
  }

  goToFirstPage() {
    this.page = 0;
    this.goToPageEvent.emit(this.page);
  }

  goToLastPage() {
    this.page = (this.totalPages as number) - 1;
    this.goToPageEvent.emit(this.page);
  }

  goToPreviousPage() {
    this.page--;
    this.goToPageEvent.emit(this.page);
  }

  goToNextPage() {
    this.page++;
    this.goToPageEvent.emit(this.page);
  }

  get isLastPage() {
    return this.page === (this.totalPages as number) - 1;
  }

}
