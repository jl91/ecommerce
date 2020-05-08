import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';

@Component({
  selector: 'app-date-column',
  templateUrl: 'date-column.component.html'
})
export class DateColumnComponent implements OnInit {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

  constructor() {
  }

  public ngOnInit() {
  }
}
