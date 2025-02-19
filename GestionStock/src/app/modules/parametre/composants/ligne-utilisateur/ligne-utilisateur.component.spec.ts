import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneUtilisateurComponent } from './ligne-utilisateur.component';

describe('LigneUtilisateurComponent', () => {
  let component: LigneUtilisateurComponent;
  let fixture: ComponentFixture<LigneUtilisateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneUtilisateurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneUtilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
