import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsClientFournisseurComponent } from './details-client-fournisseur.component';

describe('DetailsClientFournisseurComponent', () => {
  let component: DetailsClientFournisseurComponent;
  let fixture: ComponentFixture<DetailsClientFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsClientFournisseurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsClientFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
