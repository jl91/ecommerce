import {QueryBuilder} from '../model/query/query-builder.model';
import {Filter} from '../model/query/filter.model';
import {Sort} from '../model/query/sort.model';
import {Query} from '../model/query/query.model';
import {HttpParams} from '@angular/common/http';

export class QueryBuilderService implements QueryBuilder {

  private fields: Map<string, string> = new Map<string, string>();

  private filters: Map<string, string> = new Map<string, string>();

  private sorts: Map<string, string> = new Map<string, string>();

  private limit = 10;

  private page = 1;

  constructor() {
  }

  public addFields(fields: string | Array<string>): QueryBuilder {

    if (Array.isArray(fields)) {
      fields.forEach(this.addFields.bind(this));
      return this;
    }

    if (this.fields.has(fields)) {
      return this;
    }

    this.fields.set(fields, fields);

    return this;
  }

  public addFilters(filters: Filter | Array<Filter>): QueryBuilder {

    if (Array.isArray(filters)) {
      filters.forEach(this.addFilters.bind(this));
      return this;
    }

    this.filters.set(filters.key, filters.value);
    return this;
  }

  public addSorts(sorts: Sort | Array<Sort>): QueryBuilder {

    if (Array.isArray(sorts)) {
      sorts.forEach(this.addSorts.bind(this));
      return this;
    }
    this.sorts.set(sorts.key, sorts.value);
    return this;
  }

  public build(): HttpParams {
    const query: Query = {};

    if (this.fields.size > 0) {
      query.fields = this.buildFields();
    }

    if (this.filters.size > 0) {
      query.filters = this.buildFilters();
    }

    if (this.sorts.size > 0) {
      query.sorts = this.buildSorts();
    }

    if (this.limit) {
      query.limit = this.limit;
    }

    if (this.page) {
      query.page = this.page;
    }

    let httpParams = new HttpParams();

    Object.keys(query)
      .forEach(key => {
        httpParams = httpParams.append(key, query[key]);
      });

    return httpParams;
  }

  private buildFields(): string {
    return JSON.stringify(Array.from(this.fields.values()));
  }

  private buildFilters(): string {
    const filters = [];

    this.filters
      .forEach((value: string, key: string) => {
        filters.push({
          [key]: value
        });
      });

    return JSON.stringify(filters);
  }

  private buildSorts(): string {
    const sorts = [];

    this.sorts
      .forEach((value: string, key: string) => {
        sorts.push({
          [key]: value
        });
      });

    return JSON.stringify(sorts);
  }

  public removeFields(fields: string | Array<string>): QueryBuilder {

    if (Array.isArray(fields)) {
      fields.forEach(this.removeFields.bind(this));
      return this;
    }

    if (!this.fields.has(fields)) {
      return this;
    }

    this.fields.delete(fields);

    return this;
  }

  public removeFilters(filters: Filter | Array<Filter>): QueryBuilder {

    if (Array.isArray(filters)) {
      filters.forEach(this.removeFilters.bind(this));
      return this;
    }

    if (!this.filters.has(filters.key)) {
      return this;
    }

    this.filters.delete(filters.key);

    return this;
  }

  public removeSorts(sorts: Sort | Array<Sort>): QueryBuilder {

    if (Array.isArray(sorts)) {
      sorts.forEach(this.removeSorts.bind(this));
      return this;
    }

    if (!this.sorts.has(sorts.key)) {
      return this;
    }

    this.sorts.delete(sorts.key);

    return this;
  }

  public reset(): QueryBuilder {
    this.limit = 10;
    this.page = 1;
    this.fields.clear();
    this.filters.clear();
    this.sorts.clear();
    return this;
  }

  public setFields(fields: string | Array<string>): QueryBuilder {
    this.fields.clear();
    return this.addFields(fields);
  }

  public setFilters(filters: Filter | Array<Filter>): QueryBuilder {
    this.filters.clear();
    return this.addFilters(filters);
  }

  public setLimit(limit: number): QueryBuilder {
    this.limit = limit;
    return this;
  }

  public setPage(page: number): QueryBuilder {
    this.page = page;
    return this;
  }

  public setSort(sorts: Sort | Array<Sort>): QueryBuilder {
    this.sorts.clear();
    return this.addSorts(sorts);
  }


}
