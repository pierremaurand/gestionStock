import { TestBed } from '@angular/core/testing';

import { NouveauFournisseurService } from './nouveau-fournisseur.service';

describe('NouveauFournisseurService', () => {
  let service: NouveauFournisseurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NouveauFournisseurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
