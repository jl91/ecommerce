import {Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {Column} from '../model/column.model';
import {Row} from '../model/row.model';
import {DatatableService} from '../service/datatable.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-datatable',
  templateUrl: 'datatable.component.html',
  styleUrls: [
    './datatable.component.scss'
  ],
  providers: [
    DatatableService
  ]
})
export class DatatableComponent implements OnInit, OnChanges, OnDestroy {

  @Input()
  public myColumns: Array<Column> = [
    {
      key: 'Position',
      value: 'position',
      order: 0
    },
    {
      key: 'Name',
      value: 'name',
      order: 1
    },
    {
      key: 'Weight',
      value: 'weight',
      order: 3
    },
    {
      key: 'Symbol',
      value: 'symbol',
      order: 2
    },
  ];

  public displayedColumns: Array<string>;

  @Input()
  public rows: Array<Row<{
    position: number,
    name: string,
    weight: number,
    symbol: string
  }>> = [
    {
      value: {
        position: 1,
        name: 'Hydrogen',
        weight: 1.0079,
        symbol: 'H'
      }
    },
    {
      value: {
        position: 2,
        name: 'Helium',
        weight: 4.0026,
        symbol: 'He'
      }
    },
    {
      value: {
        position: 3,
        name: 'Lithium',
        weight: 6.941,
        symbol: 'Li'
      }
    },
    {
      value: {
        position: 4,
        name: 'Beryllium',
        weight: 9.0122,
        symbol: 'Be'
      }
    },
    {
      value: {
        position: 5,
        name: 'Boron',
        weight: 10.811,
        symbol: 'B'
      }
    },
    {
      value: {
        position: 6,
        name: 'Carbon',
        weight: 12.0107,
        symbol: 'C'
      }
    },
    {
      value: {
        position: 7,
        name: 'Nitrogen',
        weight: 14.0067,
        symbol: 'N'
      }
    },
    {
      value: {
        position: 8,
        name: 'Oxygen',
        weight: 15.9994,
        symbol: 'O'
      }
    },
    {
      value: {
        position: 9,
        name: 'Fluorine',
        weight: 18.9984,
        symbol: 'F'
      }
    },
    {
      value: {
        position: 10,
        name: 'Neon',
        weight: 20.1797,
        symbol: 'Ne'
      }
    },
  ];
  private subscriptions: Subscription = new Subscription();

  constructor(
    private datatableService: DatatableService
  ) {
  }

  public ngOnInit() {
    this.processColumns();
    this.registerOnColumnsChanged();
    this.registerOnRowsChanged();
  }

  public ngOnChanges(changes: SimpleChanges): void {
    this.afterChanges(changes);
  }

  public ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  private registerOnColumnsChanged(): void {
    const subscription = this.datatableService
      .columnsChangedObservable
      .subscribe((columns: Array<Column>) => {
        this.myColumns = columns;
        this.processColumns();
      });
    this.subscriptions.add(subscription);
  }

  private registerOnRowsChanged(): void {
    const subscription = this.datatableService
      .rowsChangedObservable
      .subscribe((rows: Array<Row<any>>) => {
        this.rows = rows;
      });
    this.subscriptions.add(subscription);
  }

  private afterChanges(changes: SimpleChanges): void {
    if (changes.columns.currentValue) {
      this.processColumns();
    }
  }

  private processColumns(): void {
    this.displayedColumns = this.myColumns
      .sort(column => column.order)
      .map(column => column.value);
  }


}
