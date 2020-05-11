import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {AuthenticationService} from '../../../core/web-api/endpoints/authentication/authentication.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MenuService} from '../../../shared/component/layout/navigation/service/menu.service';
import {StorageService} from '../../../core/session/storage.service';
import {RouterTestingModule} from '@angular/router/testing';
import {UsersHttpService} from '../../../core/web-api/endpoints/users/users-http.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ],
      providers: [
        AuthenticationService,
        MenuService,
        StorageService,
        UsersHttpService
      ],
      declarations: [LoginComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
