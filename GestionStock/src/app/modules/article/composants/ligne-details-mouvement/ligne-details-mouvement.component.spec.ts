import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneDetailsMouvementComponent } from './ligne-details-mouvement.component';

describe('LigneDetailsMouvementComponent', () => {
  let component: LigneDetailsMouvementComponent;
  let fixture: ComponentFixture<LigneDetailsMouvementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneDetailsMouvementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneDetailsMouvementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
