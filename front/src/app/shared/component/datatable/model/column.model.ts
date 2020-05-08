import {ColumnTypeEnum} from './column-type.enum';

export interface Column {
  key: string;
  value: string;
  order: number;
  type: ColumnTypeEnum;
}
