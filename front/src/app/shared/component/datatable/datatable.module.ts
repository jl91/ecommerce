import {NgModule} from '@angular/core';
import {DatatableComponent} from './component/datatable.component';
import {MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {A11yModule} from '@angular/cdk/a11y';
import {PrintHeaderPipe} from './pipe/print-header.pipe';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    A11yModule
  ],
  exports: [
    DatatableComponent
  ],
  declarations: [
    DatatableComponent,
    PrintHeaderPipe
  ],
  providers: [],
})
export class DatatableModule {
}
