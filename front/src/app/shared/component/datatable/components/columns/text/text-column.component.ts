import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Row} from '../../../model/row.model';
import {DatatableService} from '../../../service/datatable.service';
import {ColumnModeEnum} from '../../../model/column-mode.enum';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {EditCell} from '../../../model/edit-cell.model';

@Component({
  selector: 'app-text-column',
  templateUrl: 'text-column.component.html'
})
export class TextColumnComponent implements OnInit {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

  @Output()
  public editInline: EventEmitter<EditCell> = new EventEmitter<EditCell>();

  public form: FormGroup;

  constructor(
    private datatableService: DatatableService,
    private formBuilder: FormBuilder
  ) {
  }

  public get isReadMode(): boolean {
    return !this.row?.state
      || (
        this.row?.state?.column === this.column
        && this.row?.state?.mode === ColumnModeEnum.READ
      );
  }

  public get isEditMode(): boolean {
    return this.row?.state?.column === this.column
      && this.row?.state?.mode === ColumnModeEnum.EDIT;
  }

  public ngOnInit() {
    if (this.datatableService.isColumnEditableInline(this.column)) {
      this.initForm();
    }
  }

  public initForm(): void {
    this.form = this.formBuilder
      .group({
        data: [
          this.row.value[this.column],
          Validators.required
        ]
      });
  }

  public preventDefault(mouseEvent: MouseEvent): void {
    mouseEvent.stopImmediatePropagation();
    mouseEvent.stopPropagation();
    mouseEvent.preventDefault();
  }

  public onSubmit(mouseEvent: MouseEvent): void {
    this.preventDefault(mouseEvent);

    if (this.form.invalid) {
      return this.form.markAllAsTouched();
    }

    this.editInline
      .emit({
        column: this.column,
        row: this.row,
        newValue: this.form.get('data').value
      });
  }

}
