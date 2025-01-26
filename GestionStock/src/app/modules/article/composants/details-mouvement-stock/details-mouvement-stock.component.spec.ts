import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsMouvementStockComponent } from './details-mouvement-stock.component';

describe('DetailsMouvementStockComponent', () => {
  let component: DetailsMouvementStockComponent;
  let fixture: ComponentFixture<DetailsMouvementStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsMouvementStockComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsMouvementStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
