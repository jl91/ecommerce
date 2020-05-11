import {Repository} from '../model/repository/repository.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Serializer} from '../model/serializer/serializer.model';
import {Resource} from '../model/resource/resource.model';
import {WebApiConfiguration} from '../model/configuration/web-api.configuration';

export abstract class BaseRepositoryService<T extends Resource> implements Repository<T> {

  constructor(
    protected httpClient: HttpClient,
    protected webApiConfiguration: WebApiConfiguration,
    protected endpoint: string,
    protected serializer: Serializer<T>
  ) {
  }

  public by(params?: any): Observable<Array<T>> {
    return this.httpClient
      .get(this.getRequestUrl(), {params})
      .pipe(map(data => this.convertToModel(data)));
  }

  public byId(id: number): Observable<T> {
    return this.httpClient
      .get(this.getRequestUrl(id))
      .pipe(map(data => this.serializer.toModel(data)));
  }

  public remove(id: number): Observable<any> {
    return this.httpClient
      .delete(this.getRequestUrl(id));
  }

  public save(data: T): Observable<T> {
    return data.id
      ? this.update(data)
      : this.insert(data);
  }

  private insert(data: T): Observable<any> {
    console.log(this.serializer.toJson(data));
    return this.httpClient
      .post(this.getRequestUrl(), this.serializer.toJson(data))
      .pipe(map(result => this.serializer.toModel(result)));
  }

  private update(data: T): Observable<any> {
    return this.httpClient
      .put(this.getRequestUrl(), this.serializer.toJson(data))
      .pipe(map(result => this.serializer.toModel(result)));
  }

  protected getRequestUrl(id?: number): string {

    const base = `${this.webApiConfiguration.basePath}/${this.endpoint}`;

    if (id) {
      return `${base}/${id}`;
    }

    return base;
  }

  private convertToModel(data: any): Array<T> {
    return data.map(item => this.serializer.toModel(item));
  }

}
