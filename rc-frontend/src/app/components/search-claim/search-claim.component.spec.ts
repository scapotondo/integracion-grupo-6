import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchClaimComponent } from './search-claim.component';

describe('SearchClaimComponent', () => {
  let component: SearchClaimComponent;
  let fixture: ComponentFixture<SearchClaimComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchClaimComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchClaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
