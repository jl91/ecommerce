import {NgModule} from '@angular/core';
import {DatatableComponent} from './components/datatable/datatable.component';
import {MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {PrintHeaderPipe} from './pipe/print-header.pipe';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {TextColumnComponent} from './components/columns/text/text-column.component';
import {NumberColumnComponent} from './components/columns/number/number-column.component';
import {LongTextColumnComponent} from './components/columns/long-text/long-text-column.component';
import {MoneyColumnComponent} from './components/columns/money/money-column.component';
import {ChipsColumnComponent} from './components/columns/chips/chips-column.component';
import {DateColumnComponent} from './components/columns/date/date-column.component';
import {TimeColumnComponent} from './components/columns/time/time-column.component';
import {DatetimeColumnComponent} from './components/columns/datetime/datetime-column.component';
import {BooleanColumnComponent} from './components/columns/boolean/boolean-column.component';
import {EditableCellComponent} from './components/columns/editable-cell/editable-cell.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatIconModule,
    MatButtonModule,
    MatTooltipModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  exports: [
    DatatableComponent,
  ],
  declarations: [
    DatatableComponent,
    EditableCellComponent,
    TextColumnComponent,
    NumberColumnComponent,
    LongTextColumnComponent,
    MoneyColumnComponent,
    ChipsColumnComponent,
    DateColumnComponent,
    TimeColumnComponent,
    DatetimeColumnComponent,
    BooleanColumnComponent,
    PrintHeaderPipe,
  ],
  providers: [],
})
export class DatatableModule {
}
