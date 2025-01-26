import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsCommandeClientFournisseurComponent } from './details-commande-client-fournisseur.component';

describe('DetailsCommandeClientFournisseurComponent', () => {
  let component: DetailsCommandeClientFournisseurComponent;
  let fixture: ComponentFixture<DetailsCommandeClientFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsCommandeClientFournisseurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsCommandeClientFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
