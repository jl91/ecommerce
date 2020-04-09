import {Injectable} from '@angular/core';
import {AuthenticationService} from '../../../core/authentication/authentication.service';
import {StorageService} from '../../../core/session/storage.service';
import {AuthenticationModel} from '../../../core/authentication/authentication.model';
import {Router} from '@angular/router';
import {UsersHttpService} from '../../../core/users/users-http.service';


@Injectable()
export class LoginService {

  private username: string;

  constructor(
    private authenticationService: AuthenticationService,
    private storageService: StorageService,
    private router: Router,
    private usersHttpService: UsersHttpService
  ) {
  }


  public login(username: string, password: string): void {
    this.username = username;
    this.authenticationService
      .authenticate(username, password)
      .subscribe(
        this.processLogin.bind(this),
        console.error.bind(console)
      );
  }

  private processLogin(data: AuthenticationModel): void {
    this.authenticationService.save(data);

    if (!this.authenticationService.hasCredentials) {
      return;
    }

    // @ts-ignore
    this.usersHttpService
      .fetchUsersByUsername(this.username)
      .subscribe(console.log.bind(console));

  }
}
