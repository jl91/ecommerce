import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';
import {DatatableService} from '../../../service/datatable.service';
import {ColumnModeEnum} from '../../../model/column-mode.enum';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {ColumnState} from '../../../model/column-state.model';

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

  private columnStateSubject: Subject<ColumnState> = new BehaviorSubject<ColumnState>({
    column: null,
    row: null,
    mode: ColumnModeEnum.READ
  });

  constructor(
    private datatableService: DatatableService
  ) {
  }

  public get isEditable(): boolean {
    return this.isMouseOver
      && this.datatableService.isColumnEditableInline(this.column);
  }

  public get columnState(): Observable<ColumnState> {
    return this.columnStateSubject.asObservable();
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
    this.dispatchColumnStateMessage(ColumnModeEnum.EDIT);
  }

  public onCancelEditClick(mouseEvent: MouseEvent): void {
    this.preventDefault(mouseEvent);
    this.editMode = false;
    this.dispatchColumnStateMessage(ColumnModeEnum.READ);
  }

  private dispatchColumnStateMessage(mode: ColumnModeEnum): void {
    const {row, column} = this;
    this.columnStateSubject
      .next({
        row,
        column,
        mode
      });
  }

  private preventDefault(event: Event): void {
    event.stopImmediatePropagation();
    event.stopPropagation();
    event.preventDefault();
  }

}
