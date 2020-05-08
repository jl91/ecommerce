import {NgModule} from '@angular/core';
import {DatatableComponent} from './component/datatable.component';
import {MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {A11yModule} from '@angular/cdk/a11y';
import {PrintHeaderPipe} from './pipe/print-header.pipe';
import {MatPaginatorModule} from '@angular/material/paginator';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
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
