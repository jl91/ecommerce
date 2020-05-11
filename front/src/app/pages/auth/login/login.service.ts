import {Injectable} from '@angular/core';
import {AuthenticationService} from '../../../core/web-api/endpoints/authentication/authentication.service';
import {StorageService} from '../../../core/session/storage.service';
import {AuthenticationModel} from '../../../core/web-api/endpoints/authentication/authentication.model';
import {Router} from '@angular/router';
import {UsersHttpService} from '../../../core/web-api/endpoints/users/users-http.service';
import {User} from '../../../core/web-api/endpoints/users/user.model';
import {AuthenticationEnum} from '../../../core/web-api/endpoints/authentication/authentication.enum';


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
    this.username = username.toLowerCase();
    this.authenticationService
      .authenticate(this.username, password)
      .subscribe(
        this.processAuthentication.bind(this),
        console.error.bind(console)
      );
  }

  private processAuthentication(authentication: AuthenticationModel): void {
    this.authenticationService
      .save(AuthenticationEnum.AUTH_KEY, authentication);

    if (!this.authenticationService.hasCredentials) {
      return;
    }

    const subscription = this.usersHttpService
      .fetchUsersByUsername(this.username)
      .subscribe((user: User) => {

        this.authenticationService
          .save(AuthenticationEnum.LOGGED_USER_KEY, user);

        this.router.navigate(['/restricted']);
        subscription.unsubscribe();
      });
  }
}
