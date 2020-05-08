import {NgModule} from '@angular/core';
import {DatatableComponent} from './components/datatable/datatable.component';
import {MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {PrintHeaderPipe} from './pipe/print-header.pipe';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {TextColumnComponent} from './components/columns/text/text-column.component';
import {NumberColumnComponent} from './components/columns/number/number-column.component';
import {LongTextComponent} from './components/columns/long-text/long-text.component';

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
    TextColumnComponent,
    NumberColumnComponent,
    LongTextComponent,
    PrintHeaderPipe,
  ],
  providers: [],
})
export class DatatableModule {
}
