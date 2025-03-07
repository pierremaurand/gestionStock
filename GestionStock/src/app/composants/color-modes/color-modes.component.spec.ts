import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColorModesComponent } from './color-modes.component';

describe('ColorModesComponent', () => {
  let component: ColorModesComponent;
  let fixture: ComponentFixture<ColorModesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ColorModesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColorModesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
