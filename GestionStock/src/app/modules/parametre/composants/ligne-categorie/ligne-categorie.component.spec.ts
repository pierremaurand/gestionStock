import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneCategorieComponent } from './ligne-categorie.component';

describe('LigneCategorieComponent', () => {
  let component: LigneCategorieComponent;
  let fixture: ComponentFixture<LigneCategorieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneCategorieComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneCategorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
