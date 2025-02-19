import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneClientComponent } from './ligne-client.component';

describe('LigneClientComponent', () => {
  let component: LigneClientComponent;
  let fixture: ComponentFixture<LigneClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneClientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
