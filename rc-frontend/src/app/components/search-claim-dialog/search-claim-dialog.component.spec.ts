import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchClaimDialogComponent } from './search-claim-dialog.component';

describe('SearchClaimDialogComponent', () => {
  let component: SearchClaimDialogComponent;
  let fixture: ComponentFixture<SearchClaimDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchClaimDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchClaimDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
