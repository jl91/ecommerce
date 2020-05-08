import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';

@Component({
  selector: 'app-number-column',
  templateUrl: 'number-column.component.html'
})
export class NumberColumnComponent implements OnInit {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

  constructor() {
  }

  public ngOnInit() {
  }
}
