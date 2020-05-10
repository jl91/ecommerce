import {Row} from './row.model';
import {Subject} from 'rxjs';

export interface EditCell {
  newValue: string;
  row: Row<any>;
  column: string;
  feedbackSubject: Subject<boolean>;
}
