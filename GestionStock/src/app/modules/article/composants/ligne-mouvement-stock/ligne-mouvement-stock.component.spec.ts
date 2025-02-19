import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneMouvementStockComponent } from './ligne-mouvement-stock.component';

describe('LigneMouvementStockComponent', () => {
  let component: LigneMouvementStockComponent;
  let fixture: ComponentFixture<LigneMouvementStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneMouvementStockComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneMouvementStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
