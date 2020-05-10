import {Component, EventEmitter, forwardRef, Inject, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Row} from '../../../model/row.model';
import {EditableCellComponent} from '../editable-cell/editable-cell.component';
import {DatatableService} from '../../../service/datatable.service';
import {ColumnState} from '../../../model/column-state.model';
import {ColumnModeEnum} from '../../../model/column-mode.enum';
import {EditCell} from '../../../model/edit-cell.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Subject, Subscription} from 'rxjs';

@Component({
  selector: 'app-long-text-column',
  templateUrl: 'long-text-column.component.html'
})
export class LongTextColumnComponent implements OnInit, OnDestroy {

  @Input()
  public row: Row<any>;

  @Input()
  public column: string;

  @Output()
  public editInline: EventEmitter<EditCell> = new EventEmitter<EditCell>();

  public form: FormGroup;

  public subscriptions: Subscription = new Subscription();

  public isEditMode = false;

  public isReadMode = true;

  private feedbackObservable: Subject<boolean> = new Subject<boolean>();

  constructor(
    private datatableService: DatatableService,
    private formBuilder: FormBuilder,
    @Inject(forwardRef(() => EditableCellComponent))
    private parent: EditableCellComponent
  ) {
  }

  public ngOnInit() {
    if (this.datatableService.isColumnEditableInline(this.column)) {
      this.initForm();
    }

    this.registerOnFeedbackObservable();
    this.registerOnColumnStateChanged();
  }

  public registerOnColumnStateChanged(): void {
    const subscription = this.parent
      .columnState
      .subscribe((columnState: ColumnState) => {

        if (
          this.column !== columnState.column
          || this.row !== columnState.row
        ) {
          return;
        }

        this.isEditMode = (columnState.mode === ColumnModeEnum.EDIT);
        this.isReadMode = (columnState.mode === ColumnModeEnum.READ);
        this.parent.editMode = this.isEditMode;

      });
    this.subscriptions.add(subscription);
  }

  public registerOnFeedbackObservable(): void {
    const subscription = this.feedbackObservable
      .asObservable()
      .subscribe((done: boolean) => {

        if (done) {
          this.parent.editMode = false;
          this.isEditMode = false;
          this.isReadMode = true;
          return;
        }

        this.parent.editMode = true;
        this.isEditMode = true;
        this.isReadMode = false;
      });
    this.subscriptions.add(subscription);
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
        newValue: this.form.get('data').value,
        feedbackSubject: this.feedbackObservable
      });
  }

  public ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }


}
