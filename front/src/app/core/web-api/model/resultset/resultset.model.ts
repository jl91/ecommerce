import {PaginationMetadata} from './pagination-metadata.model';

export interface Resultset<T> {
  status: number;
  data: Array<T>;
  paginationMetadata: PaginationMetadata;
}
