import {NgModule} from '@angular/core';
import {LoginComponent} from "./login/login.component";
import {AuthRoutingModule} from "./auth-routing.module";
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";
import {MatInputModule} from "@angular/material/input";
import {SharedModule} from "../../shared/shared.module";
import {LayoutModule} from "../../shared/component/layout/layout.module";

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AuthRoutingModule,
    MatFormFieldModule,
    MatCardModule,
    MatButtonModule,
    MatDividerModule,
    MatInputModule,
    SharedModule,
    LayoutModule
  ],
  exports: [
    AuthRoutingModule,
    LoginComponent
  ],
  declarations: [
    LoginComponent
  ],
  providers: [],
})
export class AuthModule {
}
