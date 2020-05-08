import {ColumnTypeEnum} from './column-type.enum';
import {ColumnMetadata} from './column-metadata.model';

export interface Column {
  key: string;
  value: string;
  order: number;
  type: ColumnTypeEnum;
  isEditable?: boolean;
  metadata?: ColumnMetadata;
}
