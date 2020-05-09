import {Row} from './row.model';

export interface EditCell {
  newValue: string;
  row: Row<any>;
  column: string;
}
