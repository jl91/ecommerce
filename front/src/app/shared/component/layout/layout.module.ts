import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {NavigationComponent} from './navigation/navigation.component';
import {RouterModule} from '@angular/router';
import {MatMenuModule} from '@angular/material/menu';
import {MatDividerModule} from '@angular/material/divider';
import {MatBadgeModule} from '@angular/material/badge';
import {HeaderModule} from './header/header.module';
import {MatTreeModule} from '@angular/material/tree';
import {MenuService} from './navigation/service/menu.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatDividerModule,
    MatBadgeModule,
    HeaderModule,
    MatTreeModule
  ],
  exports: [
    HeaderModule,
    NavigationComponent,
  ],
  declarations: [
    NavigationComponent
  ],
  providers: [
    MenuService
  ],
})
export class LayoutModule {
}
