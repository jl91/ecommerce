import {NgModule} from '@angular/core';
import {DatatableComponent} from './components/datatable/datatable.component';
import {MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {PrintHeaderPipe} from './pipe/print-header.pipe';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCheckboxModule} from '@angular/material/checkbox';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatCheckboxModule
  ],
  exports: [
    DatatableComponent,
  ],
  declarations: [
    DatatableComponent,
    PrintHeaderPipe,
  ],
  providers: [],
})
export class DatatableModule {
}
