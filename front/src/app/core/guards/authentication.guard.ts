import {Injectable} from '@angular/core';
import {CanLoad, Route, Router} from '@angular/router';
import {AuthenticationService} from '../authentication/authentication.service';

@Injectable()
export class AuthenticationGuard implements CanLoad {

  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) {
  }

  canLoad(route: Route): boolean {

    if (this.authentication.hasLoggedUser) {
      return true;
    }

    this.router.navigate(['/']);
    return false;
  }
}
