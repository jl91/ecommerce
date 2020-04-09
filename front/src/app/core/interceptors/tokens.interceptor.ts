import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../authentication/authentication.service';
import {environment} from '../../../environments/environment';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private authenticationService: AuthenticationService
  ) {
  }

  private get basicAuthorizationHeader(): HttpHeaders {
    const {client_id, client_secret} = environment;
    const headers = new HttpHeaders();

    return headers.append(
      'Authorization',
      `Basic ${btoa(`${client_id}:${client_secret}`)}`
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

    // @ts-ignore
    return next.handle(request.clone({
        headers: this.basicAuthorizationHeader
      })
    );

  }

}
