import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneFournisseurComponent } from './ligne-fournisseur.component';

describe('LigneFournisseurComponent', () => {
  let component: LigneFournisseurComponent;
  let fixture: ComponentFixture<LigneFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneFournisseurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
