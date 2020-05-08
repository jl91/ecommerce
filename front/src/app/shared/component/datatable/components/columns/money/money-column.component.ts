import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';

@Component({
  selector: 'app-money-column',
  templateUrl: 'money-column.component.html'
})
export class MoneyColumnComponent implements OnInit {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

  constructor() {
  }

  public ngOnInit() {
  }
}
