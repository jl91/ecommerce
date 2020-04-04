import {NgModule} from '@angular/core';
import {HeaderComponent} from "./header/header.component";
import {CommonModule} from "@angular/common";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {NavigationComponent} from './navigation/navigation.component';
import {RouterModule} from "@angular/router";

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
  ],
  exports: [
    HeaderComponent,
    NavigationComponent
  ],
  declarations: [
    HeaderComponent,
    NavigationComponent
  ],
  providers: [],
})
export class LayoutModule {
}
