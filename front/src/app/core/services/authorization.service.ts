import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthorizationModel} from '../model/authorization.model';
import {environment} from '../../../environments/environment';
import {map} from 'rxjs/operators';

@Injectable()
export class AuthorizationService {

  constructor(private httpClient: HttpClient) {

  }

  private get url(): string {
    return `${environment.apiBaseUrl}/oauth/token`;
  }

  private get authorizationHeader(): HttpHeaders {
    const {client_id, client_secret} = environment;
    const headers = new HttpHeaders();

    return headers.append(
      'Authorization',
      `Basic ${btoa(`${client_id}:${client_secret}`)}`
    );
  }

  public login(username: string, password: string): Observable<AuthorizationModel> {
    const body = new FormData();
    body.append('grant_type', 'password');
    body.append('scope', 'all');
    body.append('username', username);
    body.append('password', password);
    return this.httpClient
      .post(
        this.url,
        body,
        {
          headers: this.authorizationHeader
        },
      )
      .pipe(map((result: any) => {
        return result as AuthorizationModel;
      }));
  }


}
