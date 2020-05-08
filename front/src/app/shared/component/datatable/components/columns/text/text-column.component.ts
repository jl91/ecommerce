import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';

@Component({
  selector: 'app-text-column',
  templateUrl: 'text-column.component.html'
})
export class TextColumnComponent implements OnInit {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

  constructor() {
  }

  public ngOnInit() {
  }

}
