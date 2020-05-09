import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../../model/row.model';
import {DatatableService} from '../../../service/datatable.service';
import {ColumnModeEnum} from '../../../model/column-mode.enum';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-text-column',
  templateUrl: 'text-column.component.html'
})
export class TextColumnComponent implements OnInit {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

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
      console.log(this.row.value[this.column]);
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

    }
  }

}
