import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VueEnsembleComponent } from './vue-ensemble.component';

describe('VueEnsembleComponent', () => {
  let component: VueEnsembleComponent;
  let fixture: ComponentFixture<VueEnsembleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VueEnsembleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VueEnsembleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
