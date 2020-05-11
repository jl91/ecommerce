import {Filter} from './filter.model';
import {Sort} from './sort.model';
import {HttpParams} from '@angular/common/http';

export interface QueryBuilder {

  // Fields
  setFields(fields: string | Array<string>): QueryBuilder;

  addFields(fields: string | Array<string>): QueryBuilder;

  removeFields(fields: string | Array<string>): QueryBuilder;

  // Filters
  setFilters(filters: Filter | Array<Filter>): QueryBuilder;

  addFilters(filters: Filter | Array<Filter>): QueryBuilder;

  removeFilters(filters: Filter | Array<Filter>): QueryBuilder;

  // Sort
  setSort(sorts: Sort | Array<Sort>): QueryBuilder;

  addSorts(sorts: Sort | Array<Sort>): QueryBuilder;

  removeSorts(sorts: Sort | Array<Sort>): QueryBuilder;

  // Pagination

  setLimit(limit: number): QueryBuilder;

  setPage(page: number): QueryBuilder;

  // Builder
  build(): HttpParams;

  reset(): QueryBuilder;

}
