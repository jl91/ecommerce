import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../endpoints/authentication/authentication.service';
import {WebApiConfiguration} from '../model/configuration/web-api.configuration';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private authenticationService: AuthenticationService,
    private webApiConfiguration: WebApiConfiguration
  ) {
  }

  private get basicAuthorizationHeader(): HttpHeaders {
    const {clientId, clientSecret} = this.webApiConfiguration;
    const headers = new HttpHeaders();

    return headers.append(
      'Authorization',
      `Basic ${btoa(`${clientId}:${clientSecret}`)}`
    );
  }

  private get bearerAuthorizationHeader(): HttpHeaders {
    const headers = new HttpHeaders();

    const credentials = this.authenticationService.credentials;

    return headers.append(
      'Authorization',
      `Bearer ${credentials.access_token}`
    );
  }

  public intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    if (this.authenticationService.hasCredentials) {
      const headers = this.bearerAuthorizationHeader;
      return next.handle(request.clone({
          headers
        }
      ));
    }

    return next.handle(request.clone({
        headers: this.basicAuthorizationHeader
      })
    );

  }

}
