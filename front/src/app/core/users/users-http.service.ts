import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';

@Injectable()
export class UsersHttpService {

  private readonly BASE_URL: string = `${environment.apiBaseUrl}/users`;

  constructor(private httpClient: HttpClient) {
  }

  public fetchUsersByUsername(username: string): Observable<any> {
    const url  = `${this.BASE_URL}?username=${username}`;
    return this.httpClient
      .get(url);
  }


}
