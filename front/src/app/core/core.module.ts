import {NgModule} from '@angular/core';
import {AuthorizationService} from './services/authorization.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [],
  declarations: [],
  providers: [
    AuthorizationService
  ],
})
export class CoreModule {
}
