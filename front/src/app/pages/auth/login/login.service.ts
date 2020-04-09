import {Injectable} from '@angular/core';
import {AuthenticationService} from '../../../core/authentication/authentication.service';
import {StorageService} from '../../../core/session/storage.service';
import {AuthenticationModel} from '../../../core/authentication/authentication.model';
import {StorageTypeEnum} from '../../../core/session/storage-type.enum';
import {AuthenticationEnum} from '../../../core/authentication/authentication.enum';
import {Router} from '@angular/router';


@Injectable()
export class LoginService {

  constructor(
    private authorizationService: AuthenticationService,
    private storageService: StorageService,
    private router: Router
  ) {
  }


  public login(username: string, password: string): void {
    this.authorizationService
      .login(username, password)
      .subscribe(
        this.processLogin.bind(this),
        console.error.bind(console)
      );
  }

  private processLogin(data: AuthenticationModel): void {
    const storage = this.getConfiguredSessionStorage();
    storage.setItem('data', data);
    this.router.navigate(['/restricted']);
  }

  public getConfiguredSessionStorage(): StorageService {
    return this.storageService
      .setStorageType(StorageTypeEnum.SESSION_STORAGE)
      .setNamespace(AuthenticationEnum.NAMESPACE);
  }

}
