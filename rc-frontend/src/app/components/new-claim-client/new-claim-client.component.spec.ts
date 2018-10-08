import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewClaimClientComponent } from './new-claim-client.component';

describe('NewClaimClientComponent', () => {
  let component: NewClaimClientComponent;
  let fixture: ComponentFixture<NewClaimClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewClaimClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewClaimClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
