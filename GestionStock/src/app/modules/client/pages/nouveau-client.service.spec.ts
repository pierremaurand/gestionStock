import { TestBed } from '@angular/core/testing';

import { NouveauClientService } from './nouveau-client.service';

describe('NouveauClientService', () => {
  let service: NouveauClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NouveauClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
