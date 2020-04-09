import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationModel} from './authentication.model';
import {environment} from '../../../environments/environment';
import {map} from 'rxjs/operators';
import {StorageService} from '../session/storage.service';
import {StorageTypeEnum} from '../session/storage-type.enum';
import {AuthenticationEnum} from './authentication.enum';

@Injectable()
export class AuthenticationService {

  constructor(
    private httpClient: HttpClient,
    private storageService: StorageService,
  ) {

  }

  private get url(): string {
    return `${environment.apiBaseUrl}/oauth/token`;
  }

  public authenticate(username: string, password: string): Observable<AuthenticationModel> {
    const body = new FormData();
    body.append('grant_type', 'password');
    body.append('scope', 'all');
    body.append('username', username);
    body.append('password', password);
    return this.httpClient
      .post(
        this.url,
        body
      )
      .pipe(map((result: any) => {
        return result as AuthenticationModel;
      }));
  }

  public get hasCredentials(): boolean {
    return this.authenticationStorage
      .hasItem(AuthenticationEnum.AUTH_KEY);
  }

  public get hasLoggedUser(): boolean {
    return this.authenticationStorage
      .hasItem(AuthenticationEnum.LOGGED_USER_KEY);
  }

  public get credentials(): AuthenticationModel {
    return this.authenticationStorage
      .getItem(AuthenticationEnum.AUTH_KEY);
  }

  public get authenticationStorage(): StorageService {
    return this.storageService
      .setStorageType(StorageTypeEnum.SESSION_STORAGE)
      .setNamespace(AuthenticationEnum.NAMESPACE);
  }

  public save(key: AuthenticationEnum, data: any) {
    this.authenticationStorage
      .setItem(key, data);
  }

  public logout(): void {

  }


}
