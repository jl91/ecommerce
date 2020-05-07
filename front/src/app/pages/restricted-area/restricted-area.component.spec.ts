import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RestrictedAreaComponent} from './restricted-area.component';
import {MenuService} from '../../shared/component/layout/navigation/service/menu.service';
import {RouterTestingModule} from '@angular/router/testing';

describe('RestrictedAreaComponent', () => {
  let component: RestrictedAreaComponent;
  let fixture: ComponentFixture<RestrictedAreaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        RestrictedAreaComponent
      ],
      providers: [
        MenuService
      ]
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
