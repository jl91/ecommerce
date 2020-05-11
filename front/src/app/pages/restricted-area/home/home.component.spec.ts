import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HomeComponent} from './home.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {AuthenticationService} from '../../../core/web-api/endpoints/authentication/authentication.service';
import {MenuService} from '../../../shared/component/layout/navigation/service/menu.service';
import {StorageService} from '../../../core/session/storage.service';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      declarations: [
        HomeComponent
      ],
      providers: [
        AuthenticationService,
        MenuService,
        StorageService
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
