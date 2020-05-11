import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptor} from './interceptors/tokens.interceptor';
import {AuthenticationGuard} from './guards/authentication.guard';
import {WebApiModule} from './web-api/web-api.module';
import {environment} from '../../environments/environment';

@NgModule({
  imports: [
    HttpClientModule,
    WebApiModule.forRoot(() => ({
      basePath: environment.apiBaseUrl,
      clientId: environment.client_id,
      clientSecret: environment.client_secret
    }))
  ],
  providers: [
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
