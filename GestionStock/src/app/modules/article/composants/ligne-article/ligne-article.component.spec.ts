import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LigneArticleComponent } from './ligne-article.component';

describe('LigneArticleComponent', () => {
  let component: LigneArticleComponent;
  let fixture: ComponentFixture<LigneArticleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LigneArticleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LigneArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
