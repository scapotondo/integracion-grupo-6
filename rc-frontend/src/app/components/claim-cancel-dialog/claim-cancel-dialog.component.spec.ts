import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimCancelDialogComponent } from './claim-cancel-dialog.component';

describe('ClaimCancelDialogComponent', () => {
  let component: ClaimCancelDialogComponent;
  let fixture: ComponentFixture<ClaimCancelDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClaimCancelDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClaimCancelDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
