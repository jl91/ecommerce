import {ModuleWithProviders, NgModule, Optional, SkipSelf} from '@angular/core';
import {AuthenticationService} from './endpoints/authentication/authentication.service';
import {StorageService} from '../session/storage.service';
import {UsersHttpService} from './endpoints/users/users-http.service';
import {ProductsHttpService} from './endpoints/products/products-http.service';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {WebApiConfiguration} from './model/configuration/web-api.configuration';
import {TokenInterceptor} from './interceptors/tokens.interceptor';

@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [],
  providers: [
    // Services
    AuthenticationService,
    StorageService,
    UsersHttpService,
    ProductsHttpService,
    // Request Interceptors
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
  ],
})
export class WebApiModule {
  constructor(
    @Optional() @SkipSelf() parentModule: WebApiModule,
    @Optional() http: HttpClient
  ) {

    if (parentModule) {
      throw new Error('WebApiModule is already loaded. Import in your base CoreModule only.');
    }

    if (!http) {
      throw new Error('You need to import the HttpClientModule in your CoreModule! \n' +
        'See also https://github.com/angular/angular/issues/20575');
    }
  }

  public static forRoot(configurationFactory: () => WebApiConfiguration): ModuleWithProviders {
    return {
      ngModule: WebApiModule,
      providers: [
        {
          provide: WebApiConfiguration,
          useFactory: configurationFactory
        }
      ]
    };
  }
}
