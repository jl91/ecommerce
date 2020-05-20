import {ChangeDetectionStrategy, Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Subscription} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: 'search.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SearchComponent implements OnInit, OnDestroy {

  public formGroup: FormGroup;

  @Output()
  public searchValueChanged: EventEmitter<string> = new EventEmitter();

  private readonly DEFAULT_DEBOUNCE_TIME = 300;

  private subscriptions: Subscription = new Subscription();

  constructor(private formBuilder: FormBuilder) {
  }

  private initForm(): void {
    this.formGroup = this.formBuilder
      .group({
        search: [
          ''
        ]
      });
  }

  public ngOnInit() {
    this.initForm();
    this.registerOnSearchValueChanges();
  }

  private registerOnSearchValueChanges(): void {
    const subscription = this.formGroup
      .get('search')
      .valueChanges
      .pipe(debounceTime(this.DEFAULT_DEBOUNCE_TIME))
      .subscribe(value => this.searchValueChanged.emit(value));
    this.subscriptions.add(subscription);
  }

  public ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
