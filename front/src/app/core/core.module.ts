import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
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
  ],
})
export class CoreModule {
}
