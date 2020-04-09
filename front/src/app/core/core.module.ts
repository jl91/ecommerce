import {NgModule} from '@angular/core';
import {AuthenticationService} from './authentication/authentication.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {StorageService} from './session/storage.service';
import {UsersHttpService} from './users/users-http.service';
import {TokenInterceptor} from './interceptors/tokens.interceptor';
import {AuthenticationGuard} from './guards/authentication.guard';

@NgModule({
  imports: [
    HttpClientModule
  ],
  providers: [
    // Services
    AuthenticationService,
    StorageService,
    UsersHttpService,
    // Guards
    AuthenticationGuard,
    // Request Interceptors
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
  ],
})
export class CoreModule {
}
