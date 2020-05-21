import {SortTypeEnum} from './sort-type.enum';

export interface ColumnSort {
  column: string;
  type: SortTypeEnum;
}
