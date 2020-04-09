import {NgModule} from '@angular/core';
import {AuthenticationService} from './authentication/authentication.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {StorageService} from './session/storage.service';
import {UsersHttpService} from './users/users-http.service';
import {TokenInterceptor} from './interceptors/tokens.interceptor';

@NgModule({
  imports: [
    HttpClientModule
  ],
  providers: [
    AuthenticationService,
    StorageService,
    UsersHttpService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
  ],
})
export class CoreModule {
}
