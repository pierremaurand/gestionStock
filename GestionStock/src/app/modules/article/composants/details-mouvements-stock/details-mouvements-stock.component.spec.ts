import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsMouvementsStockComponent } from './details-mouvements-stock.component';

describe('DetailsMouvementsStockComponent', () => {
  let component: DetailsMouvementsStockComponent;
  let fixture: ComponentFixture<DetailsMouvementsStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsMouvementsStockComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsMouvementsStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
