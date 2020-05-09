import {ColumnModeEnum} from './column-mode.enum';

export interface Row<T> {
  value: T;
  position: number;
  state?: {
    column: string,
    mode: ColumnModeEnum
  };
}
