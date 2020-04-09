import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {User} from './user.model';

@Injectable()
export class UsersHttpService {

  private readonly BASE_URL: string = `${environment.apiBaseUrl}/users`;

  constructor(private httpClient: HttpClient) {
  }

  public fetchUsersByUsername(username: string): Observable<any> {
    const url = `${this.BASE_URL}?username=${username}`;
    return this.httpClient
      .get(url)
      .pipe(map((data: Array<any>) => {
        const rawUser = data[0];
        const {id, name, createdAt, role} = rawUser;

        return {
          id,
          name,
          username: rawUser.username,
          createdAt,
          role: {
            id: role.id,
            name: role.name,
            createdAt: role.createdAt
          }
        } as unknown as User;
      }));
  }


}
