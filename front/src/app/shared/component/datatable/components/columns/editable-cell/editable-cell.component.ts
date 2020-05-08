import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';
import {DatatableService} from '../../../service/datatable.service';

@Component({
  selector: 'app-editable-cell',
  templateUrl: 'editable-cell.component.html',
  styleUrls: [
    './editable-cell.component.scss'
  ]
})
export class EditableCellComponent implements OnInit {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

  public isMouseOver = false;

  constructor(
    private datatableService: DatatableService
  ) {
  }

  public get isEditable(): boolean {
    return this.isMouseOver
      && this.datatableService.isColumnEditableInline(this.column);
  }

  public ngOnInit() {
  }

  public onMouseOver(
    mouseEvent: MouseEvent,
    row: Row<any>,
    column: string
  ): void {
    this.preventDefault(mouseEvent);
    this.isMouseOver = true;
  }

  public onMouseOut(
    mouseEvent: MouseEvent,
    row: Row<any>,
    column: string
  ): void {
    this.preventDefault(mouseEvent);
    this.isMouseOver = false;
  }

  private preventDefault(event: Event): void {
    event.stopImmediatePropagation();
    event.stopPropagation();
    event.preventDefault();
  }
}
