import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {User} from './user.model';
import {BaseRepositoryService} from '../../repository/base-repository.service';
import {WebApiConfiguration} from '../../model/configuration/web-api.configuration';

@Injectable()
export class UsersHttpService extends BaseRepositoryService<User> {

  constructor(
    protected httpClient: HttpClient,
    protected webApiConfiguration: WebApiConfiguration
  ) {
    super(
      httpClient,
      webApiConfiguration,
      'users',
      new User()
    );
  }

  public fetchUsersByUsername(username: string): Observable<any> {
    const url = `${super.getRequestUrl()}?username=${username}`;
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
