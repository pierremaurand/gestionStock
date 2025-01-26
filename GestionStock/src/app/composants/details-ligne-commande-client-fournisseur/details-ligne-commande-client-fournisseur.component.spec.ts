import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsLigneCommandeClientFournisseurComponent } from './details-ligne-commande-client-fournisseur.component';

describe('DetailsLigneCommandeClientFournisseurComponent', () => {
  let component: DetailsLigneCommandeClientFournisseurComponent;
  let fixture: ComponentFixture<DetailsLigneCommandeClientFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsLigneCommandeClientFournisseurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsLigneCommandeClientFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
