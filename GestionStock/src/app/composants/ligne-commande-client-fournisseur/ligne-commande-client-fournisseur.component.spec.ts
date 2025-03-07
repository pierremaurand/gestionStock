import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneCommandeClientFournisseurComponent } from './ligne-commande-client-fournisseur.component';

describe('LigneCommandeClientFournisseurComponent', () => {
  let component: LigneCommandeClientFournisseurComponent;
  let fixture: ComponentFixture<LigneCommandeClientFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneCommandeClientFournisseurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneCommandeClientFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
