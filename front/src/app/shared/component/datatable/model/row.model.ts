import {ColumnState} from './column-state.model';

export interface Row<T> {
  value: T;
  position: number;
  state?: ColumnState;
}
