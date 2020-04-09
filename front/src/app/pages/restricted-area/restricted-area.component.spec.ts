import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RestrictedAreaComponent} from './restricted-area.component';

describe('RestrictedAreaComponent', () => {
  let component: RestrictedAreaComponent;
  let fixture: ComponentFixture<RestrictedAreaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RestrictedAreaComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestrictedAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
