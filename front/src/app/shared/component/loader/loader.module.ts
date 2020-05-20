import {NgModule} from '@angular/core';
import {LoaderComponent} from './loader.component';
import {OverlayModule} from '@angular/cdk/overlay';
import {CommonModule} from '@angular/common';
import {MatCardModule} from '@angular/material/card';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {LoaderContentComponent} from './component/loader-content.component';

@NgModule({
  imports: [
    CommonModule,
    OverlayModule,
    MatCardModule,
    MatProgressSpinnerModule
  ],
  exports: [
    LoaderComponent,
  ],
  declarations: [
    LoaderComponent,
    LoaderContentComponent
  ],
  providers: [],
})
export class LoaderModule {
}
