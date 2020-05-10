import {ColumnModeEnum} from './column-mode.enum';
import {Row} from './row.model';

export interface ColumnState {
  column: string;
  row: Row<any>;
  mode: ColumnModeEnum;
}
