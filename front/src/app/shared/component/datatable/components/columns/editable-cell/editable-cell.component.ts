import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';
import {DatatableService} from '../../../service/datatable.service';
import {ColumnModeEnum} from '../../../model/column-mode.enum';

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

  public editMode = false;

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

  public onMouseEnter(mouseEvent: MouseEvent): void {
    this.preventDefault(mouseEvent);
    this.isMouseOver = true;
  }

  public onMouseLeave(mouseEvent: MouseEvent): void {
    this.preventDefault(mouseEvent);
    if (!this.editMode) {
      this.isMouseOver = false;
    }
  }

  public onEditClick(mouseEvent: MouseEvent): void {
    this.preventDefault(mouseEvent);
    this.editMode = true;
    this.row.state = {
      column: this.column,
      mode: ColumnModeEnum.EDIT
    };
  }

  public onCancelEditClick(mouseEvent: MouseEvent): void {
    this.preventDefault(mouseEvent);
    this.editMode = false;
    this.row.state = {
      column: this.column,
      mode: ColumnModeEnum.READ
    };
  }

  private preventDefault(event: Event): void {
    event.stopImmediatePropagation();
    event.stopPropagation();
    event.preventDefault();
  }

}
