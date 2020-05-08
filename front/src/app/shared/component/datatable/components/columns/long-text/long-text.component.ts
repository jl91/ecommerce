import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';

@Component({
  selector: 'app-long-text',
  templateUrl: 'long-text.component.html'
})
export class LongTextComponent implements OnInit {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

  constructor() {
  }

  public ngOnInit() {
  }

}
