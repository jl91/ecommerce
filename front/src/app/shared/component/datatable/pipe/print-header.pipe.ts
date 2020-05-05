import {Pipe, PipeTransform} from '@angular/core';
import {Column} from '../component/column.model';

@Pipe({
  name: 'printHeader'
})
export class PrintHeaderPipe implements PipeTransform {

  public transform(key: string, columns: Array<Column>): string {

    return columns
      .find(column => column.value === key)
      ?.key || null;
  }

}
