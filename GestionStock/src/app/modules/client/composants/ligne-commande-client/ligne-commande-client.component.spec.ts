import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneCommandeClientComponent } from './ligne-commande-client.component';

describe('LigneCommandeClientComponent', () => {
  let component: LigneCommandeClientComponent;
  let fixture: ComponentFixture<LigneCommandeClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneCommandeClientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneCommandeClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
