import {NgModule} from '@angular/core';
import {AuthenticationService} from './authentication/authentication.service';
import {HttpClientModule} from '@angular/common/http';
import {StorageService} from './session/storage.service';

@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [],
  declarations: [],
  providers: [
    AuthenticationService,
    StorageService
  ],
})
export class CoreModule {
}
