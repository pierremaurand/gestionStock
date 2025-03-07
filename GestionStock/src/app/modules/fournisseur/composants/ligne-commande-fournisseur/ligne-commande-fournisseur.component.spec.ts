import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneCommandeFournisseurComponent } from './ligne-commande-fournisseur.component';

describe('LigneCommandeFournisseurComponent', () => {
  let component: LigneCommandeFournisseurComponent;
  let fixture: ComponentFixture<LigneCommandeFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneCommandeFournisseurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneCommandeFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
